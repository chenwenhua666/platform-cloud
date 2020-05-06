package com.plm.platform.tx.manager;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author crystal
 */
@SpringBootApplication
@EnableTransactionManagerServer
public class PlatformTxManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformTxManagerApplication.class, args);
    }

}
