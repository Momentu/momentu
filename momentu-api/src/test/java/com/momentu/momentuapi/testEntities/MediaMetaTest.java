package com.momentu.momentuapi.testEntities;

import com.momentu.momentuapi.entities.Hashtag;
import com.momentu.momentuapi.entities.Location;
import com.momentu.momentuapi.entities.MediaMeta;
import com.momentu.momentuapi.entities.UserLike;
import com.momentu.momentuapi.entities.keys.UserLikeKey;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.Date;
import javax.xml.crypto.Data;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

//This is a test class for MediaMeta class
public class MediaMetaTest {

    //This is a test object from MediaMeta class
    MediaMeta mediaMetaTest;

    //Testing MediaMeta constructor without parameters
    @Test
    public void testMediaMeta() {
        try {
            mediaMetaTest = new MediaMeta();
            assertTrue(mediaMetaTest.getUserId() == null);
        } catch (Exception e) {
            fail("MediaMeta constructor without parameters can't be used");
        }
    }

    //Testing userId set method
    @Test
    public void testSetUserId() {
        try {
            mediaMetaTest = new MediaMeta();
            Long userId = 1L;
            mediaMetaTest.setUserId(userId);
            assertEquals(userId, mediaMetaTest.getUserId());
        }catch (Exception e){
            fail("userId value has not been set correctly");
        }
    }

    //Testing userId get method
    @Test
    public void testGetUserId(){
        try {
            mediaMetaTest = new MediaMeta();
            Long userId = 1L;
            mediaMetaTest.setUserId(userId);
            assertEquals(userId, mediaMetaTest.getUserId());
        }catch (Exception e){
            fail("userId value has not been gotten correctly");
        }
    }

    //Testing locationId set method
    @Test
    public void testSetLocationId() {
        try {
            mediaMetaTest = new MediaMeta();
            Long locationId = 1L;
            Location location = new Location("Chicago", "IL");
            location.setId(locationId);
            mediaMetaTest.setLocation(location);
            mediaMetaTest.setLocationId(locationId);
            assertEquals(locationId, mediaMetaTest.getLocationId());
        }catch (Exception e){
            fail("locationId value has not been set correctly");
        }
    }

    //Testing locationId get method
    @Test
    public void testGetLocationId(){
        try {
            mediaMetaTest = new MediaMeta();
            Long locationId = 1L;
            Location location = new Location("Chicago", "IL");
            location.setId(locationId);
            mediaMetaTest.setLocation(location);
            mediaMetaTest.setLocationId(locationId);
            assertEquals(locationId, mediaMetaTest.getLocationId());
        }catch (Exception e){
            fail("locationId value has not been gotten correctly");
        }
    }

    //Testing location set method
    @Test
    public void testSetLocation() {
        try {
            mediaMetaTest = new MediaMeta();
            Long locationId = 1L;
            Location location = new Location("Chicago", "IL");
            location.setId(locationId);
            mediaMetaTest.setLocation(location);
            mediaMetaTest.setLocationId(locationId);
            assertEquals(location, mediaMetaTest.getLocation());
        }catch (Exception e){
            fail("location value has not been set correctly");
        }
    }

    //Testing location get method
    @Test
    public void testGetLocation(){
        try {
            mediaMetaTest = new MediaMeta();
            Long locationId = 1L;
            Location location = new Location("Chicago", "IL");
            location.setId(locationId);
            mediaMetaTest.setLocation(location);
            mediaMetaTest.setLocationId(locationId);
            assertEquals(location, mediaMetaTest.getLocation());
        }catch (Exception e){
            fail("location value has not been gotten correctly");
        }
    }

    //Testing hashtagLabel set method
    @Test
    public void testSetHashtagLabel() {
        try {
            mediaMetaTest = new MediaMeta();
            String hashtagLabel = "hashtagLabelTest";
            mediaMetaTest.setHashtagLabel(hashtagLabel);
            assertEquals(hashtagLabel, mediaMetaTest.getHashtagLabel());
        }catch (Exception e){
            fail("hashtagLabel value has not been set correctly");
        }
    }

    //Testing hashtagLabel get method
    @Test
    public void testGetHashtagLabel(){
        try {
            mediaMetaTest = new MediaMeta();
            String hashtagLabel = "hashtagLabelTest";
            mediaMetaTest.setHashtagLabel(hashtagLabel);
            assertEquals(hashtagLabel, mediaMetaTest.getHashtagLabel());
        }catch (Exception e){
            fail("hashtagLabel value has not been gotten correctly");
        }
    }

    //Testing removed set method
    @Test
    public void testSetRemoved() {
        try {
            mediaMetaTest = new MediaMeta();
            boolean removed = true;
            mediaMetaTest.setRemoved(removed);
            assertEquals(true, mediaMetaTest.isRemoved());
        }catch (Exception e){
            fail("removed value has not been set correctly");
        }
    }

    //Testing isRemoved method
    @Test
    public void testIsRemoved(){
        try {
            mediaMetaTest = new MediaMeta();
            boolean removed = true;
            mediaMetaTest.setRemoved(removed);
            assertEquals(true, mediaMetaTest.isRemoved());
        }catch (Exception e){
            fail("isRemoved method has not been implemented correctly");
        }
    }

    //Testing created set method
    @Test
    public void testSetCreated() {
        try {
            mediaMetaTest = new MediaMeta();
            Date created = new Date();
            mediaMetaTest.setCreated(created);
            assertEquals(created, mediaMetaTest.getCreated());
        }catch (Exception e){
            fail("created value has not been set correctly");
        }
    }

    //Testing created get method
    @Test
    public void testGetCreated(){
        try {
            mediaMetaTest = new MediaMeta();
            Date created = new Date();
            mediaMetaTest.setCreated(created);
            assertEquals(created, mediaMetaTest.getCreated());
        }catch (Exception e){
            fail("created method has not been gotten correctly");
        }
    }

}
