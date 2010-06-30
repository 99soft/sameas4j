package com.asemantics.sameas;

/**
 * Default factory class to build a concrete implementation of {@link com.asemantics.sameas.SameAsService}.
 *
 * @see com.asemantics.sameas.SameAsServiceImpl
 * @author Davide Palmisano (dpalmisano@gmail.com)
 *
 */
public class DefaultSameAsServiceFactory {

    public static SameAsService getService(Class<? extends SameAsService> clazz) {
        if(clazz.equals(SameAsService.class))
            return new SameAsServiceImpl();
        throw new IllegalArgumentException(
                String.format(
                        "Class %s cannot be instantiated as a valid SameAsService implementation",
                        clazz.getName()
                )
        );
    }

}
