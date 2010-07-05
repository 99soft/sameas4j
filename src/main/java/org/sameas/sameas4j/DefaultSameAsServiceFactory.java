package org.sameas.sameas4j;

/**
 * Default factory class to build a concrete implementation of {@link org.sameas.sameas4j.SameAsService}.
 *
 * @see org.sameas.sameas4j.SameAsServiceImpl
 * @author Davide Palmisano (dpalmisano@gmail.com)
 * @version $Id$
 *
 */
public class DefaultSameAsServiceFactory {

    private final static SameAsService INSTANCE = new SameAsServiceImpl();

    /**
     * 
     * @return
     * @since 1.1
     */
    public static SameAsService createNew() {
        return new SameAsServiceImpl();
    }

    /**
     * 
     * @return
     * @since 1.1
     */
    public static SameAsService getSingletonInstance() {
        return INSTANCE;
    }

    /**
     * 
     * @param clazz
     * @return
     * @deprecated
     */
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
