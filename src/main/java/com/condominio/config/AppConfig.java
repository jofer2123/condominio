package com.condominio.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.condominio.entity.Registro;
import com.condominio.entity.Usuarios;

@Configuration
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.condominio.dao"), @ComponentScan("com.condominio.service"),
		@ComponentScan("com.condominio.entity"), @ComponentScan("com.condominio.config"),
		@ComponentScan("com.condominio.bean"), @ComponentScan("com.github.adminfaces.template")
		
})

/**
 * Clase encargada de configurar conexiones de base de datos.
 * 
 * @author marval.
 * @version 1.0.
 */
public class AppConfig {

	/**
	 * Obtiene datasource para realizar conexión a BD.
	 * 
	 * @return DataSource.
	 */

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource driver = new DriverManagerDataSource();
	    driver.setUrl("jdbc:postgresql://192.168.0.14:5432/condominio");
	    driver.setUsername("postgres");
	    driver.setPassword("123456");
	    return driver;
	}

	/**
	 * crea objeto de sesión para conectar a BD.
	 * 
	 * @return LocalSessionFactoryBean.
	 * @version 1.0.
	 * @since 1.0.
	 */
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(getDataSource());

		Properties props = new Properties();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		props.put("hibernate.connection.driver_class", "org.postgresql.Driver");
		props.put("hibernate.id.new_generator_mappings", "true");
		factoryBean.setHibernateProperties(props);
		
		
		/* se agregan clases bean para trabajar con hibernate*/
		factoryBean.setAnnotatedClasses(
				Usuarios.class,Registro.class
				);

		return factoryBean;
	}

	/**
	 * @return HibernateTransactionManager.
	 */
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
}