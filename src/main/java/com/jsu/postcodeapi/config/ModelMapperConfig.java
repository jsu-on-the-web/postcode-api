package com.jsu.postcodeapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jsu.postcodeapi.converters.StringTrimConverter;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		
		// mapper will take in DTO (Create or update)
		// will turn DTO into Post so I can save it in db
		// replace the need for using constructors
		// or setters
		
		ModelMapper mapper = new ModelMapper();
		// set up my options here
		// trim strings
		mapper.typeMap(String.class, String.class).setConverter(new StringTrimConverter());
		mapper.getConfiguration().setSkipNullEnabled(true);
		return mapper;
		
	}

}
