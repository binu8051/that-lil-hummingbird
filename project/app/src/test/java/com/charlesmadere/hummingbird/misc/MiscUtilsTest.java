package com.charlesmadere.hummingbird.misc;

import android.os.Build;

import com.charlesmadere.hummingbird.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.M)
public class MiscUtilsTest {

    @Test
    public void testBooleanEquals() throws Exception {
        assertTrue(MiscUtils.booleanEquals(null, null));

        assertTrue(MiscUtils.booleanEquals(Boolean.TRUE, Boolean.TRUE));
        assertTrue(MiscUtils.booleanEquals(Boolean.TRUE, true));
        assertTrue(MiscUtils.booleanEquals(true, Boolean.TRUE));
        assertTrue(MiscUtils.booleanEquals(true, true));

        assertTrue(MiscUtils.booleanEquals(Boolean.FALSE, Boolean.FALSE));
        assertTrue(MiscUtils.booleanEquals(Boolean.FALSE, false));
        assertTrue(MiscUtils.booleanEquals(false, Boolean.FALSE));
        assertTrue(MiscUtils.booleanEquals(false, false));

        assertFalse(MiscUtils.booleanEquals(Boolean.TRUE, Boolean.FALSE));
        assertFalse(MiscUtils.booleanEquals(Boolean.TRUE, false));
        assertFalse(MiscUtils.booleanEquals(Boolean.TRUE, null));
        assertFalse(MiscUtils.booleanEquals(true, Boolean.FALSE));
        assertFalse(MiscUtils.booleanEquals(true, false));
        assertFalse(MiscUtils.booleanEquals(true, null));

        assertFalse(MiscUtils.booleanEquals(Boolean.FALSE, Boolean.TRUE));
        assertFalse(MiscUtils.booleanEquals(Boolean.FALSE, true));
        assertFalse(MiscUtils.booleanEquals(Boolean.FALSE, null));
        assertFalse(MiscUtils.booleanEquals(false, Boolean.TRUE));
        assertFalse(MiscUtils.booleanEquals(false, true));
        assertFalse(MiscUtils.booleanEquals(false, null));
    }

    @Test
    public void testExclusiveAdd() throws Exception {
        final ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        MiscUtils.exclusiveAdd(list, Arrays.asList(1, 2, 3));
        assertTrue(list.size() == 3);

        MiscUtils.exclusiveAdd(list, Arrays.asList(1, 2, 3, 6));
        assertTrue(list.size() == 4);

        MiscUtils.exclusiveAdd(list, Arrays.asList(10, 20, 30, 1));
        assertTrue(list.size() == 7);

        MiscUtils.exclusiveAdd(list, Arrays.asList(30, 3, 2, 1, 6));
        assertTrue(list.size() == 7);

        MiscUtils.exclusiveAdd(list, null);
        assertTrue(list.size() == 7);

        MiscUtils.exclusiveAdd(list, new ArrayList<Integer>());
        assertTrue(list.size() == 7);

        MiscUtils.exclusiveAdd(list, new LinkedList<Integer>());
        assertTrue(list.size() == 7);
    }

    @Test
    public void testIntegerEquals() throws Exception {
        assertTrue(MiscUtils.integerEquals(null, null));

        assertTrue(MiscUtils.integerEquals(-1, -1));
        assertTrue(MiscUtils.integerEquals(0, 0));
        assertTrue(MiscUtils.integerEquals(1, 1));

        assertFalse(MiscUtils.integerEquals(0, null));
        assertFalse(MiscUtils.integerEquals(null, 0));

        assertFalse(MiscUtils.integerEquals(-1, 1));
        assertFalse(MiscUtils.integerEquals(1, -1));
    }

    @Test
    public void testIsValidArtwork() throws Exception {
        assertFalse(MiscUtils.isValidArtwork(null));
        assertFalse(MiscUtils.isValidArtwork(""));
        assertFalse(MiscUtils.isValidArtwork(" "));
        assertFalse(MiscUtils.isValidArtwork("  "));

        assertTrue(MiscUtils.isValidArtwork("Hello, World!"));
        assertTrue(MiscUtils.isValidArtwork("http://www.google.com/"));

        for (final String missingArtwork : Constants.MISSING_ARTWORK) {
            assertFalse(MiscUtils.isValidArtwork(missingArtwork));
        }
    }

    @Test
    public void testToArrayList() throws Exception {
        assertNull(MiscUtils.toArrayList(null));
        assertNotNull(MiscUtils.toArrayList(Collections.EMPTY_LIST));
        assertNotNull(new ArrayList<>());

        ArrayList<Object> array = new ArrayList<>();
        assertTrue(array == MiscUtils.toArrayList(array));

        LinkedList<Integer> linked = new LinkedList<>();
        assertNotNull(MiscUtils.toArrayList(linked));
        assertTrue(linked.size() == MiscUtils.toArrayList(linked).size());

        linked.add(1);
        linked.add(2);
        assertNotNull(MiscUtils.toArrayList(linked));
        assertTrue(linked.size() == 2);
        assertTrue(linked.get(0) == 1);
        assertTrue(linked.get(1) == 2);
    }

}
