package com.plm.platform.server.statistics.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author crystal
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSourceVO{

    @NotBlank(message = "{required}")
    private String dataSourceName;

    @NotBlank(message = "{required}")
    private String databaseName;
}
