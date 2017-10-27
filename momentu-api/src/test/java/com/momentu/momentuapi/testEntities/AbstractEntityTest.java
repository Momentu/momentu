package com.momentu.momentuapi.testEntities;

import com.momentu.momentuapi.entities.AbstractEntity;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

//This is a test class for Abstract Entity class
public class AbstractEntityTest {

    //This is a test object from Abstract Entity class
    AbstractEntity abstractEntityTest = new AbstractEntity();

    //Testing id set method in Abstract Entity class
    @Test
    public void testSetId() {
        try {
            Long id = 1L;
            Long expectedId = 1L;
            abstractEntityTest.setId(id);
            assertEquals(expectedId, abstractEntityTest.getId());
        } catch (Exception e) {
            fail("The id value has not been set");
        }
    }

    //Testing id get method in Abstract Entity class
    @Test
    public void testGetId() {
        try {
            Long id = 1L;
            Long expectedId = 1L;
            abstractEntityTest.setId(id);
            assertEquals(expectedId, abstractEntityTest.getId());
        } catch (Exception e) {
            fail("The id value has not been gotten");
        }
    }
}