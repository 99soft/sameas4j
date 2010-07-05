package org.sameas.sameas4j;

/**
 * Default factory class to build a concrete implementation of {@link org.sameas.sameas4j.SameAsService}.
 *
 * @see org.sameas.sameas4j.SameAsServiceImpl
 * @author Davide Palmisano (dpalmisano@gmail.com)
 *
 */
public class DefaultSameAsServiceFactory {

    private final static SameAsService INSTANCE = new SameAsServiceImpl();

    public static SameAsService createNew() {
        return new SameAsServiceImpl();
    }

    public static SameAsService getSingletonInstance() {
        return INSTANCE;
    }

    @Deprecated
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
