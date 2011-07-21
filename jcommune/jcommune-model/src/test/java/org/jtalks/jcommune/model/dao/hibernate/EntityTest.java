package org.jtalks.jcommune.model.dao.hibernate;

import org.jtalks.jcommune.model.entity.Entity;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author Kirill Afonin
 */
public class EntityTest {
    private class EntityObject extends Entity {
        public EntityObject(String uuid) {
            this.setUuid(uuid);
        }
        
        public EntityObject() {
            this.setUuid(java.util.UUID.randomUUID().toString());
        }
    }

    private Entity first;
    private Entity second;
    private Entity third;

    @Test
    public void testEqualsSymmetry() {
        String uuid = java.util.UUID.randomUUID().toString();
        
        first = new EntityObject(uuid);
        second = new EntityObject(uuid);

        assertTrue(first.equals(second));
        assertTrue(second.equals(first));
    }

    @Test
    public void testEqualsReflexivity() {
        first = new EntityObject();

        assertTrue(first.equals(first));
    }

    @Test
    public void testEqualsNull() {
        first = new EntityObject();

        assertFalse(first.equals(null));
    }

    @Test
    public void testEqualsTransitivity() {
        String uuid = java.util.UUID.randomUUID().toString();
        
        first = new EntityObject(uuid);
        second = new EntityObject(uuid);
        third = new EntityObject(uuid);

        assertTrue(first.equals(second));
        assertTrue(second.equals(third));
        assertTrue(first.equals(third));
    }

    @Test
    public void testEqualsWhenDifferentId() {
        first = new EntityObject("id1");
        second = new EntityObject("id2");

        assertFalse(first.equals(second));
        assertFalse(second.equals(first));
    }

    @Test
    public void testEqualsWhenDifferentClasses() {
        String id = "id";
        first = new EntityObject(id);
        second = new Entity() {
        };
        second.setUuid(id);

        assertFalse(first.equals(second));
    }

    @Test
    public void testHashCode() {
        first = new EntityObject("uid1");
        second = new EntityObject("uid1");

        assertEquals(first.hashCode(), second.hashCode());
    }
}