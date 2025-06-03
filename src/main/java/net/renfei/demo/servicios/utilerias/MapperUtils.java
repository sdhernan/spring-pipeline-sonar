/**
 * 
 */
package net.renfei.demo.servicios.utilerias;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

/**
 * The Interface MapperUtils.
 *
 * @author sdhernan
 */
public final class MapperUtils {

	/** The model mapper. */
	private static ModelMapper modelMapper = new ModelMapper();

	/**
	 * Model mapper property setting are specified in the following block. Default
	 * property matching strategy is set to Strict see {@link MatchingStrategies}
	 * Custom mappings are added using {@link ModelMapper#addMappings(PropertyMap)}
	 */
	static {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Instantiates a new mapper utils.
	 */
	private MapperUtils() {
		super();
	}

	/**
	 * Map.
	 *
	 * @param <D>      the generic type
	 * @param <T>      the generic type
	 * @param entity   the entity
	 * @param outClass the out class
	 * @return the d
	 */
	public static <D, T> D map(final T entity, Class<D> outClass) {
		return modelMapper.map(entity, outClass);
	}

	/**
	 * Map all.
	 *
	 * @param <D>        the generic type
	 * @param <T>        the generic type
	 * @param entityList the entity list
	 * @param outCLass   the out C lass
	 * @return the list
	 */
	public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
		return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
	}

}
