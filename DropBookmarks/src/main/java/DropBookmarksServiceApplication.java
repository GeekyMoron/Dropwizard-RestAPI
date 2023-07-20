import auth.DropAuthenticator;
import core.User;
import dao.UserDao;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import mapper.BookMarksMapper;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import resources.HelloResource;
import resources.UserResource;


public class DropBookmarksServiceApplication extends Application<DropBookmarksServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new DropBookmarksServiceApplication().run(args);
    }
    @Override
    public void run(DropBookmarksServiceConfiguration configuration, Environment environment) throws Exception {
    environment.jersey().register(new HelloResource());
    environment.jersey().register(new AuthDynamicFeature(
        new BasicCredentialAuthFilter.Builder<User>()
            .setAuthenticator(new DropAuthenticator(configuration.getPassword()))
            .setRealm("SUPER SECRET STUFF")
            .buildAuthFilter()));
    //register mybatis
        ManagedDataSource dataSource = configuration.getDataSourceFactory().build(environment.metrics(),"mysql");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        org.apache.ibatis.mapping.Environment env = new org.apache.ibatis.mapping.Environment("development", transactionFactory, dataSource);
        Configuration config = new Configuration(env);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
            .build(config);
        Configuration myBatisConfiguration = sqlSessionFactory.getConfiguration();
        // add mapper to my batis
        myBatisConfiguration.addMapper(BookMarksMapper.class);
        UserDao  userDao =  new UserDao(sqlSessionFactory);
        environment.jersey().register(new UserResource(userDao));
    }
    @Override
    public void initialize(final Bootstrap<DropBookmarksServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<DropBookmarksServiceConfiguration>() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(DropBookmarksServiceConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }
}
