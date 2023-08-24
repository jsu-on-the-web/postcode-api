package com.jsu.postcodeapi.converters;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class StringTrimConverter implements Converter<String, String>{

	/**
	 * Convert the given MappingContext to a String.
	 *
	 * @param  context  the MappingContext to be converted
	 * @return          the converted String, or null if the source is null
	 */
	@Override
	public String convert(MappingContext<String, String> context) {
		
		if(context.getSource() == null) {
			return null;
		}
		
		return context.getSource().trim();
	}

}
