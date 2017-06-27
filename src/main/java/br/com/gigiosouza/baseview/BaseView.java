package br.com.gigiosouza.baseview;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

public abstract class BaseView {

	static Logger log = LoggerFactory.getLogger(BaseView.class);

	public static <E, T> List<E> fromModel(List<T> listT, Class<E> clazz) {
		List<E> listE = new ArrayList<E>();

		for (T t : listT) {
			try {
				listE.add(fromModel(t, clazz.newInstance()));
			} catch (Throwable e1) {
				throw new RuntimeException("Erro ao converter model para view", e1);
			}
		}

		return listE;
	}

	public static <E, T> List<E> fromModel(List<T> listT, Class<E> clazz, String group) {
		List<E> listE = new ArrayList<E>();

		for (T t : listT) {
			try {
				listE.add(fromModel(t, clazz.newInstance(), group));
			} catch (Throwable e1) {
				throw new RuntimeException("Erro ao converter model para view", e1);
			}
		}

		return listE;
	}

	public static <E, T> List<E> toModel(List<T> listT, Class<E> clazzDest) {
		List<E> listE = new ArrayList<E>();

		for (T t : listT) {
			try {
				listE.add(toModel(t, clazzDest.newInstance()));
			} catch (Throwable e1) {
				throw new RuntimeException("Erro ao converter model para view", e1);
			}
		}

		return listE;
	}
	
	public static <E, T> List<E> toModel(List<T> listT, Class<E> clazzDest, String group) {
		List<E> listE = new ArrayList<E>();

		for (T t : listT) {
			try {
				listE.add(toModel(t, clazzDest.newInstance(), group));
			} catch (Throwable e1) {
				throw new RuntimeException("Erro ao converter model para view", e1);
			}
		}

		return listE;
	}

	public static <E, T> E toModel(T t, E e) {
		return toModel(t, e, new String[0]);
	}

	public static <E, T> E toModel(T t, E e, String group) {
		return toModel(t, e, new String[] { group });
	}

	public static <E, T> E toModel(T t, E e, String[] groups) {
		try {
			List<String> groupsList = Arrays.asList(groups);

			for (Field source : t.getClass().getDeclaredFields()) {
				ModelProp modelProp = source.getAnnotation(ModelProp.class);
				if (modelProp != null && (modelProp.group().isEmpty() || groupsList.contains(modelProp.group()))) {
					try {
						source.setAccessible(true);

						if (modelProp.view()) {

							Field field = e.getClass().getDeclaredField(modelProp.value());

							Object obj = toModel(source.get(t), field.getType().newInstance());
							setFieldValue(e, obj, modelProp.value());

						} else {
							Object val = source.get(t);
							setFieldValue(e, val, modelProp.value());
						}
					} catch (Exception ex) {
						log.trace("Erro ao associar model prop {} ignorando , erro {}", modelProp.value(),
								ex.getMessage());
						continue;
					}
				}

			}
		} catch (Throwable e1) {
			throw new RuntimeException("Erro ao converter model para view", e1);
		}

		return e;
	}

	public static <E, T> E fromModel(T t, E e, String[] groups) {
		try {
			List<String> groupsList = Arrays.asList(groups);

			Class<?> clazz = e.getClass();
			fromModelFields(t, e, groupsList, clazz);

		} catch (Throwable e1) {
			throw new RuntimeException("Erro ao converter model para view", e1);
		}

		return e;
	}

	private static <E, T> void fromModelFields(T t, E e, List<String> groupsList, Class<?> clazz) {

		if (clazz.getSuperclass() != null) {
			fromModelFields(t, e, groupsList, clazz.getSuperclass());
		}

		for (Field dest : clazz.getDeclaredFields()) {
			ModelProp modelProp = dest.getAnnotation(ModelProp.class);
			if (modelProp != null) {
				if (modelProp.group().isEmpty() || groupsList.contains(modelProp.group())) {
					dest.setAccessible(true);

					try {
						if (modelProp.view()) {
							Object obj = fromModel(getFieldValue(t, modelProp.value()), dest.getType().newInstance());
							dest.set(e, obj);
						} else {
							dest.set(e, getFieldValue(t, modelProp.value()));
						}
					} catch (Exception ex) {
						log.trace("Erro ao associar model prop {} ignorando , erro {}", modelProp.value(),
								ex.getMessage());
						continue;
					}
				}
			}

		}
	}

	private static Object getFieldValue(Object parent, String modelProp)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		String props[] = modelProp.split("\\.");
		String prop = props[0];

		PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(parent);

		Object value = myAccessor.getPropertyValue(prop);
		if (modelProp.contains(".")) {
			value = getFieldValue(value, modelProp.substring(modelProp.indexOf(".") + 1));
		}

		return value;
	}

	private static void setFieldValue(Object parent, Object val, String modelProp) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException {

		String props[] = modelProp.split("\\.");
		String prop = props[0];

		Field field = parent.getClass().getDeclaredField(prop);
		field.setAccessible(true);
		Object value = field.get(parent);
		if (modelProp.contains(".")) {
			if (val != null || value != null) {
				if (value == null) {
					value = field.getType().newInstance();
					field.set(parent, value);
				}
				field.setAccessible(true);
				setFieldValue(value, val, modelProp.substring(modelProp.indexOf(".") + 1));
			}
		} else {
			field.set(parent, val);
		}

	}

	public static <E, T> E fromModel(T t, E e, String group) {
		return fromModel(t, e, new String[] { group });

	}

	public static <E, T> E fromModel(T t, E e) {
		return fromModel(t, e, new String[0]);

	}
}