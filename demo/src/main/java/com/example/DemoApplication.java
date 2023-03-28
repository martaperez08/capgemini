package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.ioc.Rango;
import com.example.ioc.StringRepositoryImpl;
import com.example.ioc.StringService;
import com.example.ioc.StringServiceImpl;
import com.example.ioc.UnaTonteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.var;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// cunado creas la classes inyecta en StringService busca la que esta marcad por
	// @Repositoiro,
	// en el caso que tengamos otra classes con el repositorio se ejcutaria esa

	// que pasa si dos classes tiene dos @Repositorio no funciona pero podemos
	// solucionarlo 1 tener una nica calse o marcar primario añadiendo @Primary

	@Autowired
	@Qualifier("Local")
	private StringService srvLocal;

    //private StringService srvRemotoService;
	
	@Value("${mi.valor}") //para externizarla configuracion  los valores en este caso con el dolor cogemos el valor de config
	private String	config;
	
	@Autowired
	Rango rango;
	//comentario
	//aa
	@Autowired
	UnaTonteria tonteria; 
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Data @AllArgsConstructor
	class Actor {
		private int id;
		private String first_name, last_name;
		
		public Actor(int id, String first_name, String last_name) {
			super();
			this.id = id;
			this.first_name = first_name;
			this.last_name = last_name;
		}
		
		//loombok
		
	}
	
	
	class ActorRowMapper implements RowMapper<Actor> {
	      @Override
	      public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
	            return new Actor(rs.getInt("actor_id"), rs.getString("last_name"), rs.getString("first_name"));
	      }
	      
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicacion arrancada");

		// inyeccion manua
		/*
		 * StringRepositoryImpl dao= new StringRepositoryImpl(); var srv= new
		 * StringServiceImpl(dao);
		 */
		System.out.println(srvLocal.get(1));
		System.out.println(config);
		
		System.out.println(rango.getMax());
		System.out.println(rango.getMin());
		System.out.println(tonteria.dimeAlgo());
		
		
		
		var lst = jdbcTemplate.query("""
				SELECT actor_id, first_name, last_name
				from actor
				""", new ActorRowMapper()
				);
		//lst.forEach(System.out::println);
		jdbcTemplate.queryForList("""
				SELECT concat(first_name, ' ', last_name)
				from actor
				""", String.class).forEach(System.out::println);

	}

}
