# Week 8

    Suggested exercises:
    5.2, 5.3, 5.4, 5.8, 5.11.


## 5.2 Caches are important to providing a high-performance memory hierarchy to processors. Below is a list of 64-bit memory address references, given as word addresses.

  0x03, 0xb4, 0x2b, 0x02, 0xbf, 0x58, 0xbe, 0x0e, 0xb5, 0x2c, 0xba, 0xfd.


### 5.2.1 For each of these references, identify the binary word address, the tag, and the index given a direct-mapped cache with 16 one-word blocks. Also list whether each reference is a hit or a miss, assuming the cache is initially empty.

Address in binary:

00000011
10110100
00101011
00000010
10111111
01011000
10111110
00001110
10110101
00101100
10111010
11111101

one words in block = 0 bit for offset.
16 blocks in cache = 4 bits for index (2^4 = 16).
64 - 4 = 60 bits for tag.

Hit or miss table 


| addr            | tag | index | M/H |
|-----------------|-----|-------|-----|
|     00000011    |  0  |   3   |  M  |
|     10110100    |  11 |   4   |  M  |
|     00101011    |  2  |   11  |  M  |
|     00000010    |  0  |   2   |  M  |
|     10111111    |  11 |   15  |  M  |
|     01011000    |  5  |   8   |  M  |
|     10111110    |  11 |   14  |  M  |
|     00001110    |  0  |   14  |  M  |
|     10110101    |  11 |   5   |  M  |
|     00101100    |  2  |   12  |  M  |
|     10111010    |  11 |   10  |  M  |
|     11111101    |  15 |   13  |  M  |

The cache after reads:

| index |   cache  |
|:-----:|:--------:|
|   1   |          |
|   2   | 0        |
|   3   | 0        |
|   4   | 11       |
|   5   | 11       |
|   6   |          |
|   7   |          |
|   8   | 5        |
|   9   |          |
|   10  | 11       |
|   11  | 2        |
|   12  | 2        |
|   13  | 15       |
|   14  | 11,    0 |
|   15  | 11       |
|   16  |          |


### 5.2.2 For each of these references, identify the binary word address, the tag, the index, and the offset given a direct-mapped cache with two-word blocks and a total size of eight blocks. Also list if each reference is a hit or a miss, assuming the cache is initially empty.

two words in block = 1 bit for offset (2^1 = 2).
8 blocks in cache = 3 bits for index (2^3 = 8).
32 - 3 = 29 bits for tag.

| addr            | tag | index | offset | M/H |
|-----------------|-----|-------|--------|-----|
|        00000011 |  0  |   1   |    1   |  M  |
|     10110100    |  11 |   2   |    0   |  M  |
|     00101011    |  2  |   5   |    1   |  M  |
|     00000010    |  0  |   1   |    0   |  H  |
|     10111111    |  11 |   7   |    1   |  M  |
|     01011000    |  5  |   4   |    0   |  M  |
|     10111110    |  11 |   7   |    0   |  H  |
|     00001110    |  0  |   7   |    0   |  M  |
|     10110101    |  11 |   2   |    1   |  H  |
|     00101100    |  2  |   6   |    0   |  M  |
|     10111010    |  11 |   5   |    0   |  M  |
|     11111101    |  15 |   6   |    1   |  M  |



| index |  tag  | cache |   |
|:-----:|:-----:|:-----:|:-:|
|       |       |   0   | 1 |
|   0   |       |       |   |
|   1   |   0   |       |   |
|   2   |   11  |       |   |
|   3   |       |       |   |
|   4   |   5   |       |   |
|   5   | 2, 11 |       |   |
|   6   | 2, 15 |       |   |
|   7   | 11, 0 |       |   |
|   8   |       |       |   |
|   9   |       |       |   |
|   10  |       |       |   |
|   11  |       |       |   |
|   12  |       |       |   |
|   13  |       |       |   |
|   14  |       |       |   |
|   15  |       |       |   |

## 5.2.3 You are asked to optimize a cache design for the given references. There are three direct-mapped cache designs possible, all with a total of eight words of data:

* C1 has 1-word blocks,
* C2 has 2-word blocks, and
* C3 has 4-word blocks.

**C1**

one words in block = 0 bit for offset.
8 blocks in cache = 3 bits for index (2^3 = 8).
32 - 3 = 29 bits for tag.

**C2**

two words in block = 1 bit for offset (2^1 = 2).
8/2 blocks in cache = 2 bits for index (2^2 = 4).
32 - 3 - 1 = 28 bits for tag.

**C3**

4 words in block = 2 bit for offset (2^2 = 4).
8/4 blocks in cache = 1 bits for index (2^1 = 2).
32 - 3 - 2 = 27 bits for tag.


table :

|                 |     |   |   C1  |          |   |   C2  |          |   |   C3  |          |
|-----------------|-----|---|:-----:|:--------:|---|:-----:|:--------:|---|:-----:|:--------:|
|     Address     | Tag |   | Index | Hit/Miss |   | Index | Hit/Miss |   | Index | Hit/Miss |
|        00000011 |  0  |   |   3   |     M    |   |   1   |     M    |   |   0   |     M    |
|     10110100    |  22 |   |   4   |     M    |   |   2   |     M    |   |   1   |     M    |
|     00101011    |  5  |   |   3   |     M    |   |   1   |     M    |   |   0   |     M    |
|     00000010    |  0  |   |   2   |     M    |   |   1   |     M    |   |   0   |     M    |
|     10111111    |  23 |   |   7   |     M    |   |   3   |     M    |   |   1   |     M    |
|     01011000    |  11 |   |   0   |     M    |   |   0   |     M    |   |   0   |     M    |
|     10111110    |  23 |   |   6   |     M    |   |   3   |     H    |   |   1   |     H    |
|     00001110    |  1  |   |   6   |     M    |   |   3   |     M    |   |   1   |     M    |
|     10110101    |  22 |   |   5   |     M    |   |   2   |     H    |   |   1   |     M    |
|     00101100    |  5  |   |   4   |     M    |   |   2   |     M    |   |   1   |     M    |
|     10111010    |  23 |   |   2   |     M    |   |   1   |     M    |   |   0   |     M    |
|     11111101    |  31 |   |   5   |     M    |   |   2   |     M    |   |   1   | M        |


You are asked to optimize a cache design
for the given references. There are three direct-mapped cache designs possible, all with a
total of eight words of data: C1 has one-word blocks, C2 has two-word blocks, and C3
has four-word blocks. In terms of miss rate, which cache design is best? If the miss stall
time is 25 cycles, and C1 has an access time of 2 cycles, C2 takes 3 cycles, and C3 takes
5 cycles, which is the best cache design? (Every access, hit or miss, requires an access to
the cache.) 

![Alt text](../Test%20exsams/img/Screenshot%202022-12-05%20at%2021.27.35.png)

## 5.3 

By convention, a cache is named according to the amount of data it contains (i.e., a 4 KiB cache can hold 4 KiB of data); however, caches also require SRAM to store metadata such as tags and valid bits. 

For this exercise, you will examine how a cache’s configuration affects the total amount of SRAM needed to implement it as well as the performance of the cache. For all parts, assume that the caches are direct-mapped, byte addressable, and that addresses and the words are 32 bits.

### 5.3.1 Calculate the total number of bits required to implement a 32 KiB cache with two-word blocks.

Each word is 8 bytes; each block contains two words; thus, each block contains 16 = 2^4 bytes.

The cache contains 32KiB = 2^15 bytes of data. Thus, it has 2^15/2^4 = 2^11 lines of data.

Each 64-bit address is divided into: (1) a 3-bit word offset, (2) a 1-bit block offset, (3) an 11-bit index (because there are 2^11 lines), and (4) a 49-bit tag (64 − 3 − 1 −11=49).

The cache is composed of: 2^15 * 8 bits of data + 2^11*49 bits of tag + 2^11*1 valid bits = 364,544 bits.


### 5.3.2 Calculate the total number of bits required to implement a 64 KiB cache with 16-word blocks. How much bigger is this cache than the 32 KiB cache described in Exercise 5.3.1? (Notice that, by changing the block size, we doubled the amount of data without doubling the total size of the cache.)

### 5.3.3 Explain why this 64 KiB cache, despite its larger data size, might provide slower performance than the first cache.

### 5.3.4 Generate a series of read requests that have a lower miss rate on a 32 KiB two-way set associative cache than on the cache described in Exercise 5.3.1.