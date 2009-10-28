package com.asemantics.sameas;

import com.asemantics.sameas.interfaces.SameAsService;

public class SameAsServiceFactory {

    @SuppressWarnings("unchecked")
    public static SameAsService getService(Class clazz) {

        if(clazz.equals(SameAsService.class))
            return new SameAsServiceImpl();

        throw new IllegalArgumentException(
                String.format("service %s cannot be instantiated", clazz.getName()));

    }

}
