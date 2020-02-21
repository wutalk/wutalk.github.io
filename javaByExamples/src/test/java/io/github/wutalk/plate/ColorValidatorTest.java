package io.github.wutalk.plate;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ColorValidatorTest {

    private ColorValidator v = new ColorValidator();

    @Test
    public void validate() {
        List<int[]> plates = new ArrayList<int[]>();
        plates.add(new int[]{0, 1});
        plates.add(new int[]{1, 0});
        Assert.assertTrue(v.validate(plates));
        Assert.assertTrue(v.validateRefactor(plates));

        plates.clear();
        plates.add(new int[]{0, 1});
        plates.add(new int[]{0, 0});
        Assert.assertFalse(v.validate(plates));
        Assert.assertFalse(v.validateRefactor(plates));

        plates.clear();
        plates.add(new int[]{0, 1, 0});
        plates.add(new int[]{1, 0, 1});
        plates.add(new int[]{0, 1, 0});
        Assert.assertTrue(v.validate(plates));
        Assert.assertTrue(v.validateRefactor(plates));

        plates.clear();
        plates.add(new int[]{0, 1, 0});
        plates.add(new int[]{1, 0, 1});
        plates.add(new int[]{1, 1, 0});
        Assert.assertFalse(v.validate(plates));
        Assert.assertFalse(v.validateRefactor(plates));

        plates.clear();
        plates.add(new int[]{0, 1, 0, 1, 0});
        plates.add(new int[]{1, 0, 1, 0, 1});
        plates.add(new int[]{0, 1, 0, 1, 0});
        plates.add(new int[]{1, 0, 1, 0, 1});
        plates.add(new int[]{0, 1, 0, 1, 0});
        Assert.assertTrue(v.validate(plates));
        Assert.assertTrue(v.validateRefactor(plates));
    }
}