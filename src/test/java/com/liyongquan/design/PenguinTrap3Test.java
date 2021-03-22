package com.liyongquan.design;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class PenguinTrap3Test {
    private PenguinTrap3 pt3 = new PenguinTrap3();

    public static final Hex[] DIR = {
            new Hex(+1, -1, 0), new Hex(+1, 0, -1),
            new Hex(0, +1, -1), new Hex(-1, +1, 0),
            new Hex(-1, 0, +1), new Hex(0, -1, +1),
    };

    @Test
    public void next() {
        Map<Hex, BlockType> map = build();
        log.info("**********更新前***********");
        print(map, 5);
        Map<Hex, BlockType> next = pt3.next(map);
        log.info("**********更新后***********");
        print(next, 5);
    }

    private Map<Hex, BlockType> build() {
        Map<Hex, BlockType> map = new HashMap<>();
        //初始化中心点
        Hex center = new Hex(0, 0, 0);
        map.put(center, BlockType.BLOCK);
        for (int i = 1; i <= 4; i++) {
            List<Hex> hexes = cubeRing(center, i);
            for (Hex hex : hexes) {
                map.put(hex, BlockType.BLOCK);
            }
        }
        List<Hex> hexes = cubeRing(center, 5);
        for (Hex hex : hexes) {
            map.put(hex, BlockType.WALL);
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
        for (int i = 1; i <= 5; i++) {
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