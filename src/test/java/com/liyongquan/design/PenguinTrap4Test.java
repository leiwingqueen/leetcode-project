package com.liyongquan.design;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

@Slf4j
public class PenguinTrap4Test {
    public static final int RADIUS = 4;

    private PenguinTrap4 pt = new PenguinTrap4();

    private Random random = new Random();

    @Test
    public void knock() {
        Hex[] blocks = {
                new Hex(0, 0, 0),

                new Hex(1, 0, -1),
                new Hex(2, 0, -2),
                new Hex(3, 0, -3),

                new Hex(1, -1, 0),
                new Hex(2, -2, 0),
                new Hex(3, -3, 0),

                new Hex(-1, 1, 0),
                new Hex(-2, 2, 0),
                new Hex(-3, 3, 0),

                new Hex(-1, 0, 1),
                new Hex(-2, 0, 2),
                new Hex(-3, 0, 3),
        };
        Map<Hex, BlockType> map = build(RADIUS);
        int cnt = 0;
        for (Hex block : blocks) {
            List<Hex> res = pt.knock(map, Arrays.asList(block));
            cnt += res.size();
            StringBuilder sb = new StringBuilder();
            for (Hex re : res) {
                sb.append(String.format("[%s,%s,%s],", re.x, re.y, re.z));
            }
            log.info("敲击的冰块:[{},{},{}],实际掉落的冰块:{}", block.x, block.y, block.z, sb.toString());
        }
        log.info("掉落的总冰块数量:{}", cnt);
    }

    /**
     * 统计敲击多少次会完全掉落
     */
    @Test
    public void sysbench() {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            Map<Hex, BlockType> map = build(RADIUS);
            Optional<Hex> opt = pick(map);
            int cnt = 0;
            while (opt.isPresent()) {
                pt.knock(map, Arrays.asList(opt.get()));
                opt = pick(map);
                cnt++;
            }
            sum += cnt;
            log.info("敲击次数:{}", cnt);
        }
        log.info("平均敲击次数:{}", sum / 1000);
    }

    /**
     * 假设企鹅在中间的平均敲击次数
     */
    @Test
    public void sysbench2() {
        int sum = 0;
        int total = 1000;
        Hex center = new Hex(0, 0, 0);
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int i = 0; i < total; i++) {
            Map<Hex, BlockType> map = build(RADIUS);
            int cnt = 0;
            while (map.get(center) == BlockType.BLOCK) {
                Optional<Hex> opt = pick(map);
                while (opt.get().equals(center)) {
                    opt = pick(map);
                }
                pt.knock(map, Arrays.asList(opt.get()));
                cnt++;
            }
            sum += cnt;
            //log.info("敲击次数:{}", cnt);
            cntMap.put(cnt, cntMap.getOrDefault(cnt, 0) + 1);
        }
        log.info("平均敲击次数:{}", sum / total);
        for (Map.Entry<Integer, Integer> entry : cntMap.entrySet()) {
            log.info("敲击次数{}:数量{}", entry.getKey(), entry.getValue());
        }
    }

    private Optional<Hex> pick(Map<Hex, BlockType> map) {
        List<Hex> list = new ArrayList<>();
        for (Map.Entry<Hex, BlockType> entry : map.entrySet()) {
            if (entry.getValue() == BlockType.BLOCK) {
                list.add(entry.getKey());
            }
        }
        if (list.size() == 0) {
            return Optional.empty();
        }
        int i = random.nextInt(list.size());
        return Optional.of(list.get(i));
    }

    private Map<Hex, BlockType> build(int radius) {
        Map<Hex, BlockType> map = new HashMap<>();
        //初始化中心点
        int cnt = 0;
        Hex center = new Hex(0, 0, 0);
        map.put(center, BlockType.BLOCK);
        cnt += 1;
        for (int i = 1; i < radius; i++) {
            List<Hex> hexes = cubeRing(center, i);
            for (Hex hex : hexes) {
                map.put(hex, BlockType.BLOCK);
                cnt += 1;
            }
        }
        List<Hex> hexes = cubeRing(center, radius);
        for (Hex hex : hexes) {
            map.put(hex, BlockType.WALL);
        }
        //log.info("总冰块数量:{}", cnt);
        return map;
    }

    //***********************************参考https://www.redblobgames.com/grids/hexagons/#rings*************************************************************

    private List<Hex> cubeRing(Hex center, int radius) {
        List<Hex> res = new ArrayList<>(radius * 6);
        Hex cube = cubeAdd(center, cubeScale(PenguinTrap4.DIR[4], radius));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < radius; j++) {
                res.add(cube);
                cube = cubeAdd(cube, PenguinTrap4.DIR[i]);
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