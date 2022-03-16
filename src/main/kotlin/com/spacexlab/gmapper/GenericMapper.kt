package com.spacexlab.gmapper

import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField


/*
* This Singleton Class contains methods for mapping from one Data Model to another.
* It contains @see com.spacexlib.gmapper.GenericMapper.mapSingle that maps a
* single Data Model instance to the provided KClass type and
* @see com.spacexlib.gmapper.GenericMapper.mapList maps a list
* of the Data Model instances to a list of the provided KClass type
* @author Jones Omoyibo
* @since  2022-03-29
 */
object GenericMapper {

    /*
     * This method maps a single Data Model instance to the provided KClass type
     * @param from data model to extract values.
     * @param to  KClass instance of the data model type to map to
     * @return To the instance of the class  type specified by the KClass.
     * @since 0.1.0
     */
    fun <From : Any, To : Any> mapSingle(from: From, to: KClass<To>): To {
        val fromProperties: MutableMap<String, KProperty1<From, *>> =
            extractValuesFromInstance(from::class) as MutableMap<String, KProperty1<From, *>>

        return setNewInstanceValues(fromProperties, from, to)

    }


    /*
    * This method maps a list of the Data Model instances to the provided KClass type
    * @param fromList list of  data model instances to extract values.
    * @param to  KClass instance of the data model type to map to.
    * @return the list of the type of the instances to a list of the provided KClass type
    * @since 0.1.0
    */
    fun <From : Any, To : Any> mapList(fromList: List<From>, to: KClass<To>): List<To> {

        val mappedList = mutableListOf<To>()

        fromList.map {
            mappedList.add(mapSingle(it, to))
        }

        return mappedList

    }


    private fun <From : Any> extractValuesFromInstance(fromClass: KClass<From>): MutableMap<String, KProperty1<From, *>> {

        val fromProperties: MutableMap<String, KProperty1<From, *>> = mutableMapOf()

        for (fromProperty in fromClass.memberProperties) fromProperties[fromProperty.name] = fromProperty

        return fromProperties

    }


    private fun <From : Any, To : Any> setNewInstanceValues(
        fromProperties: MutableMap<String, KProperty1<From, *>>,
        from: From,
        to: KClass<To>
    ): To {

        val toInstance = to.createInstance()
        for (toProperty in to.memberProperties) {
            if (toProperty.name in fromProperties) {

                if (!toProperty.isAccessible) toProperty.isAccessible = true

                toProperty.javaField?.set(toInstance, fromProperties[toProperty.name]?.get(from))
            }

        }

        return toInstance

    }
}


