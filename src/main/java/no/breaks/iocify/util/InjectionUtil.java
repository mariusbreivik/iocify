package no.breaks.iocify.util;

import no.breaks.iocify.annotation.Autowired;
import no.breaks.iocify.annotation.Qualifier;
import no.breaks.iocify.Iocify;
import org.burningwave.core.classes.FieldCriteria;

import java.lang.reflect.Field;
import java.util.Collection;

import static org.burningwave.core.assembler.StaticComponentContainer.Fields;

public class InjectionUtil {

    public InjectionUtil() {
        super();
    }

    public static void autowire(Iocify iocify, Class<?> clazz, Object instance)
            throws IllegalAccessException, InstantiationException {

        Collection<Field> fields = Fields.findAllAndMakeThemAccessible(
                FieldCriteria.forEntireClassHierarchy().allThoseThatMatch(field ->
                        field.isAnnotationPresent(Autowired.class)
                ), clazz
        );

        for (Field field : fields) {
            String qualifier = field.isAnnotationPresent(Qualifier.class)
                    ? field.getAnnotation(Qualifier.class).value()
                    : null;

            Object fieldInstance = iocify.getBeanInstance(field.getType(),
                    field.getName(), qualifier);
            Fields.setDirect(clazz, field, fieldInstance);
            autowire(iocify, fieldInstance.getClass(), fieldInstance);
        }

    }
}
