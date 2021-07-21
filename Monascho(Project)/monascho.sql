/*
 Navicat Premium Data Transfer

 Source Server         : monascho_store
 Source Server Type    : MySQL
 Source Server Version : 100417
 Source Host           : localhost:3306
 Source Schema         : monascho

 Target Server Type    : MySQL
 Target Server Version : 100417
 File Encoding         : 65001

 Date: 21/07/2021 06:46:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for barang
-- ----------------------------
DROP TABLE IF EXISTS `barang`;
CREATE TABLE `barang`  (
  `id_barang` int NOT NULL AUTO_INCREMENT,
  `Kode_Barang` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Nama_Barang` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Jenis` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Stok` int NOT NULL,
  `Harga` int NOT NULL,
  `Tanggal_Masuk` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `kadaluwarsa` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id_barang`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of barang
-- ----------------------------
INSERT INTO `barang` VALUES (13, 'BR-1', 'Monascho Curcumin 350 ml', 'Cair', 78, 35000, '16-07-2021', '21-01-2022');
INSERT INTO `barang` VALUES (21, 'BR-2', 'Monascho Curcumix 350 ml', 'Cair', 99, 45000, '20-07-2021', '20-07-2022');
INSERT INTO `barang` VALUES (22, 'BR-3', 'Monascho Curhe 350 ml ', 'Cair', 100, 40000, '21-07-2021', '21-07-2022');
INSERT INTO `barang` VALUES (23, 'BR-4', 'Monascho Original Kental 125 ml', 'Cair', 100, 250000, '21-07-2021', '21-07-2022');

-- ----------------------------
-- Table structure for detail_transaksi
-- ----------------------------
DROP TABLE IF EXISTS `detail_transaksi`;
CREATE TABLE `detail_transaksi`  (
  `Kode_Detail` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Kode_Barang` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Harga` int NOT NULL,
  `Jumlah` int NOT NULL,
  `Discount` int NOT NULL,
  `Subtotal` int NOT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of detail_transaksi
-- ----------------------------
INSERT INTO `detail_transaksi` VALUES ('DTRX001', 'BR-2', 45000, 3, 0, 135000);
INSERT INTO `detail_transaksi` VALUES ('DTRX002', 'BR-2', 45000, 1, 0, 45000);
INSERT INTO `detail_transaksi` VALUES ('DTRX003', 'BR-1', 35000, 2, 0, 70000);

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
  `id_pengguna` int NOT NULL AUTO_INCREMENT,
  `kd_pengguna` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nama` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `alamat` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `jenis_kelamin` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `telepon` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `username` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `hak_akses` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id_pengguna`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES (3, 'P-1', 'Bayu Firdaus', 'Jalan Mahakan 2', 'Laki-Laki', '0821414521', 'bayu', 'admin', 'Admin');
INSERT INTO `login` VALUES (4, 'P-2', 'Arip Suharson', 'Jalan Patrang', 'Laki-Laki', '08216471412', 'arip', 'kasir', 'Kasir');
INSERT INTO `login` VALUES (6, 'P-3', 'Indra Wirawan', 'Jalan Tunggul Ametung', 'Laki-Laki', '0264955656', 'indro', 'heleh', 'Admin');

-- ----------------------------
-- Table structure for transaksi
-- ----------------------------
DROP TABLE IF EXISTS `transaksi`;
CREATE TABLE `transaksi`  (
  `Kode_Transaksi` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Kode_Detail` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Nama_Barang` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Tanggal` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Total` int NOT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transaksi
-- ----------------------------
INSERT INTO `transaksi` VALUES ('TRX001', 'DTRX001', 'Monascho Curcumix 350 ml', '20-07-2021', 205000);
INSERT INTO `transaksi` VALUES ('TRX002', 'DTRX002', 'Monascho Curcumix 350 ml', '20-07-2021', 150000);
INSERT INTO `transaksi` VALUES ('TRX003', 'DTRX003', 'Monascho Curcumin 350 ml', '20-07-2021', 70000);

SET FOREIGN_KEY_CHECKS = 1;
