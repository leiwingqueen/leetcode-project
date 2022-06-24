package com.liyongquan.design;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class PenguinTrap3Test {
    private PenguinTrap3 pt3 = new PenguinTrap3();

    public static final Hex[] DIR = {
            new Hex(+1, -1, 0), new Hex(+1, 0, -1),
            new Hex(0, +1, -1), new Hex(-1, +1, 0),
            new Hex(-1, 0, +1), new Hex(0, -1, +1),
    };

    public static final int RADIUS = 4;

    /**
     * 全部填满的场景
     */
    @Test
    public void test1() {
        Function<Hex, BlockType> function = hex -> {
            //初始化中心点
            Hex center = new Hex(0, 0, 0);
            //最边缘是墙壁
            List<Hex> hexes = cubeRing(center, RADIUS);
            for (Hex block : hexes) {
                if (hex.equals(block)) {
                    return BlockType.WALL;
                }
            }
            return BlockType.BLOCK;
        };
        testTpl(function, function, RADIUS);
    }

    /**
     * 全部为空
     */
    @Test
    public void test2() {
        Function<Hex, BlockType> function = hex -> {
            //初始化中心点
            Hex center = new Hex(0, 0, 0);
            //最边缘是墙壁
            List<Hex> hexes = cubeRing(center, RADIUS);
            for (Hex block : hexes) {
                if (hex.equals(block)) {
                    return BlockType.WALL;
                }
            }
            return BlockType.EMPTY;
        };
        testTpl(function, function, RADIUS);
    }

    /**
     * 一条直线
     */
    @Test
    public void test3() {
        Function<Hex, BlockType> function = hex -> {
            //初始化中心点
            Hex center = new Hex(0, 0, 0);
            //最边缘是墙壁
            List<Hex> hexes = cubeRing(center, RADIUS);
            for (Hex block : hexes) {
                if (hex.equals(block)) {
                    return BlockType.WALL;
                }
            }
            if (hex.y == 0) {
                return BlockType.BLOCK;
            }
            return BlockType.EMPTY;
        };
        testTpl(function, function, RADIUS);
    }

    /**
     * 一条直线-中心点缺失
     */
    @Test
    public void test4() {
        Function<Hex, BlockType> input = hex -> {
            //初始化中心点
            Hex center = new Hex(0, 0, 0);
            if (hex.equals(center)) {
                return BlockType.EMPTY;
            }
            //最边缘是墙壁
            List<Hex> hexes = cubeRing(center, RADIUS);
            for (Hex block : hexes) {
                if (hex.equals(block)) {
                    return BlockType.WALL;
                }
            }
            if (hex.y == 0) {
                return BlockType.BLOCK;
            }
            return BlockType.EMPTY;
        };

        Function<Hex, BlockType> output = hex -> {
            //初始化中心点
            Hex center = new Hex(0, 0, 0);
            //最边缘是墙壁
            List<Hex> hexes = cubeRing(center, RADIUS);
            for (Hex block : hexes) {
                if (hex.equals(block)) {
                    return BlockType.WALL;
                }
            }
            return BlockType.EMPTY;
        };
        testTpl(input, output, RADIUS);
    }

    /**
     * 三角形
     */
    @Test
    public void test5() {
        Function<Hex, BlockType> input = hex -> {
            //初始化中心点
            Hex center = new Hex(0, 0, 0);
            //最边缘是墙壁
            List<Hex> hexes = cubeRing(center, RADIUS);
            for (Hex block : hexes) {
                if (hex.equals(block)) {
                    return BlockType.WALL;
                }
            }
            Hex[] blocks = {
                    new Hex(0, 0, 0),

                    new Hex(-1, 0, 1),
                    new Hex(-2, 0, 2),
                    new Hex(-3, 0, 3),

                    new Hex(0, 1, -1),
                    new Hex(0, 2, -2),
                    new Hex(0, 3, -3),

                    new Hex(1, -1, 0),
                    new Hex(2, -2, 0),
                    new Hex(3, -3, 0),
            };
            for (Hex block : blocks) {
                if (block.equals(hex)) {
                    return BlockType.BLOCK;
                }
            }
            return BlockType.EMPTY;
        };
        testTpl(input, input, RADIUS);
    }

    /**
     * 才字
     */
    @Test
    public void test6() {
        Function<Hex, BlockType> input = hex -> {
            //初始化中心点
            Hex center = new Hex(0, 0, 0);
            //最边缘是墙壁
            List<Hex> hexes = cubeRing(center, RADIUS);
            for (Hex block : hexes) {
                if (hex.equals(block)) {
                    return BlockType.WALL;
                }
            }
            Hex[] blocks = {
                    new Hex(0, 0, 0),

                    new Hex(0, 1, -1),
                    new Hex(0, 2, -2),
                    new Hex(0, 3, -3),
                    new Hex(0, -1, 1),
                    new Hex(0, -2, 2),
                    new Hex(0, -3, 3),

                    new Hex(-3, 0, 3),
                    new Hex(-2, 0, 2),
                    new Hex(-1, 0, 1),
            };
            for (Hex block : blocks) {
                if (block.equals(hex)) {
                    return BlockType.BLOCK;
                }
            }
            return BlockType.EMPTY;
        };
        testTpl(input, input, RADIUS);
    }

    private void testTpl(Function<Hex, BlockType> input, Function<Hex, BlockType> output, int radius) {
        log.info("-------------------------------------------------分割线------------------------------------");
        Map<Hex, BlockType> map = build(input, radius);
        log.info("**********更新前***********");
        print(map, radius);
        Map<Hex, BlockType> res = pt3.next(map);
        log.info("**********更新后***********");
        print(res, radius);
        for (Map.Entry<Hex, BlockType> entry : res.entrySet()) {
            Assert.assertEquals(output.apply(entry.getKey()), entry.getValue());
        }
    }


    private Map<Hex, BlockType> build(Function<Hex, BlockType> blockMatch, int radius) {
        Map<Hex, BlockType> map = new HashMap<>();
        //初始化中心点
        Hex center = new Hex(0, 0, 0);
        map.put(center, blockMatch.apply(center));
        for (int i = 1; i <= radius; i++) {
            List<Hex> hexes = cubeRing(center, i);
            for (Hex hex : hexes) {
                map.put(hex, blockMatch.apply(hex));
            }
        }
        return map;
    }

    /**
     * 逐层遍历输出
     *
     * @param next
     * @param radius
     */
    private void print(Map<Hex, BlockType> map, int radius) {
        Hex center = new Hex(0, 0, 0);
        log.info("**********第{}层***********", 0);
        log.info("{}", map.get(center));
        for (int i = 1; i <= radius; i++) {
            List<Hex> hexes = cubeRing(center, i);
            log.info("**********第{}层***********", i);
            for (Hex hex : hexes) {
                log.info("[x:{},y:{},z:{}]:{}", hex.x, hex.y, hex.z, map.get(hex));
            }
        }
    }

    //***********************************参考https://www.redblobgames.com/grids/hexagons/#rings*************************************************************

    private List<Hex> cubeRing(Hex center, int radius) {
        List<Hex> res = new ArrayList<>(radius * 6);
        Hex cube = cubeAdd(center, cubeScale(DIR[4], radius));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < radius; j++) {
                res.add(cube);
                cube = cubeAdd(cube, DIR[i]);
            }
        }
        return res;
    }

    private Hex cubeAdd(Hex cube, Hex dir) {
        return new Hex(cube.x + dir.x, cube.y + dir.y, cube.z + dir.z);
    }

    private Hex cubeScale(Hex dir, int radius) {
        return new Hex(dir.x * radius, dir.y * radius, dir.z * radius);
    }
}