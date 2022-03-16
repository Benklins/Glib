package com.spacexlab.gfactory

import com.sun.corba.se.impl.util.RepositoryId.cache
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance


/*
* This Singleton Class contain single Method for creating a plain instance of the type Specified by the KClass Instance
* @author Jones Omoyibo
* @since  2022-03-29
 */
object GenericFactory {

    /*
    * This method returns the instance of the Type specified by @param{classType}
    * @param classType is the KClass instance
    * @return the instance of the class specified by the KClass.
    * @since 0.1.0
    */
    inline fun <reified ClassType : Any> instance(classType: KClass<ClassType>): ClassType {
        return if (cache.containsKey(classType.qualifiedName)) cache[classType.qualifiedName] as ClassType
        else {
            val instance = classType.createInstance()
            classType.qualifiedName?.let {
                cache.put(it, instance)
            }
            instance
        }

    }

}