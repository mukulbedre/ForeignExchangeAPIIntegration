package com.example.ForeignExchange.migration;

import java.sql.Connection;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

public class V2_Flyway_migration extends BaseJavaMigration {

	  @Override
	    public void migrate(Context context) throws Exception {
	        try (Connection connection = context.getConnection()) {
	            // Perform custom migration logic
	            connection.createStatement().execute("ALTER TABLE EXCHANGE_RATE ADD COLUMN new_column VARCHAR(255)");
	        }
	    }

}
