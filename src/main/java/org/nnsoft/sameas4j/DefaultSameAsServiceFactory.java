package org.nnsoft.sameas4j;

/**
 * Default factory class to build a concrete implementation of {@link org.nnsoft.sameas4j.SameAsService}.
 *
 * @see org.nnsoft.sameas4j.SameAsServiceImpl
 * @author Davide Palmisano (dpalmisano@gmail.com)
 * @version $Id$
 */
public final class DefaultSameAsServiceFactory {

    /**
     * The singleton {@link SameAsService} instance.
     */
    private final static SameAsService INSTANCE = new SameAsServiceImpl();

    /**
     * Hidden constructor, this class can't be instantiated
     */
    private DefaultSameAsServiceFactory() {
        // prevents this class instantiation
    }

    /**
     * Creates a new {@link SameAsService} instance.
     *
     * @return a new {@link SameAsService} instance.
     * @since 1.1
     */
    public static SameAsService createNew() {
        return new SameAsServiceImpl();
    }

    /**
     * Returns the singleton {@link SameAsService} instance.
     *
     * @return the singleton {@link SameAsService} instance.
     * @since 1.1
     */
    public static SameAsService getSingletonInstance() {
        return INSTANCE;
    }

    /**
     * Creates a new {@link SameAsService} instance.
     *
     * @param clazz the {@link SameAsService} has to be instantiated.
     * @return a new {@link SameAsService} instance.
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
