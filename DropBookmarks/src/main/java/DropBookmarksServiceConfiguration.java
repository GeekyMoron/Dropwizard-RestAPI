import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DropBookmarksServiceConfiguration extends Configuration {
    @JsonProperty
    public String getPassword() {
        return password;
    }
    //all database related params are mapped to a separate class DataSourceFactory

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty
    private String password;

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    @NotNull
    @Valid
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

}
