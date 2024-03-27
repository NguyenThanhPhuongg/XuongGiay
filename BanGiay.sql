CREATE DATABASE QUANLYBANGIAY
GO

USE QUANLYBANGIAY
GO

CREATE TABLE Size(
	ID INT IDENTITY(1,1) NOT NULL,
	Ten INT NOT NULL,
	TrangThai BIT NOT NULL,

	 CONSTRAINT [PK_KichThuoc] PRIMARY KEY (ID)
)


CREATE TABLE MauSac(
	ID INT IDENTITY(1,1) NOT NULL,
	Ten NVARCHAR(50) NOT NULL,
	TrangThai BIT NOT NULL,

	 CONSTRAINT [PK_MauSac] PRIMARY KEY (ID)
)


CREATE TABLE ChatLieu(
	ID INT IDENTITY(1,1) NOT NULL,
	Ten NVARCHAR(50) NOT NULL,
	TrangThai BIT NOT NULL,

	 CONSTRAINT [PK_ChatLieu] PRIMARY KEY (ID)
)


CREATE TABLE XuatXu(
	ID INT IDENTITY(1,1) NOT NULL,
	Ten NVARCHAR(50) NOT NULL,
	TrangThai BIT NOT NULL,

	 CONSTRAINT [PK_XuatXu] PRIMARY KEY (ID)
)


CREATE TABLE ThuongHieu(
	ID INT IDENTITY(1,1) NOT NULL,
	Ten NVARCHAR(50) NOT NULL,
	TrangThai BIT NOT NULL,

	 CONSTRAINT [PK_ThuongHieu] PRIMARY KEY (ID)
)


CREATE TABLE DanhMuc(
	ID INT IDENTITY(1,1) NOT NULL,
	Ten NVARCHAR(50) NOT NULL,
	TrangThai BIT NOT NULL,

	 CONSTRAINT [PK_DanhMuc] PRIMARY KEY (ID)
)



CREATE TABLE KhachHang(
	ID INT IDENTITY(1,1) NOT NULL,
	Ma VARCHAR(10) UNIQUE,
	Ten NVARCHAR(30) NOT NULL,
	NgaySinh DATE NOT NULL,
	GioiTinh BIT NOT NULL,
	SDT VARCHAR(10) NOT NULL,

	CONSTRAINT [PK_KhachHang] PRIMARY KEY (ID)
)


CREATE TABLE NhanVien(
	ID INT IDENTITY(1,1) NOT NULL,
	Ma VARCHAR(10) UNIQUE,
	Passwords VARCHAR (15) NOT NULL,
	Ten NVARCHAR(30) NOT NULL,
	SDT VARCHAR(10) NOT NULL,
	Email VARCHAR(30) NOT NULL,
	ChucVu BIT NOT NULL,
	TrangThai BIT NOT NULL,

	CONSTRAINT [PK_NhanVien] PRIMARY KEY (ID)
)


CREATE TABLE SanPham(
	ID INT IDENTITY(1,1) NOT NULL,
	Ma VARCHAR(10) UNIQUE,
	TenSP NVARCHAR(30) NOT NULL,
	MoTa NVARCHAR(50) NULL,
	ID_ThuongHieu INT NOT NULL,

	CONSTRAINT [PK_SanPham] PRIMARY KEY (ID),
	CONSTRAINT [FK_SanPham_ThuongHieu] FOREIGN KEY(ID_ThuongHieu) REFERENCES ThuongHieu(ID)
)


CREATE TABLE SanPhamChiTiet(
	ID INT IDENTITY(1,1) NOT NULL,
	TrangThai BIT NOT NULL,
	Gia MONEY NOT NULL,
	SoLuong INT NOT NULL,
	ID_SP INT NOT NULL,
	ID_Size INT NOT NULL,
	ID_MauSac INT NOT NULL,
	ID_ChatLieu INT NOT NULL,
	ID_XuatXu INT NOT NULL,
	ID_DanhMuc INT NOT NULL,

	CONSTRAINT [FK_CTSP_SanPham] FOREIGN KEY(ID_SP) REFERENCES SanPham (ID), 
	CONSTRAINT [FK_CTSP_Size] FOREIGN KEY(ID_Size) REFERENCES Size(ID),
	CONSTRAINT [FK_CTSP_MauSac] FOREIGN KEY(ID_MauSac) REFERENCES MauSac(ID),
	CONSTRAINT [FK_CTSP_ChatLieu] FOREIGN KEY(ID_ChatLieu) REFERENCES ChatLieu(ID),
	CONSTRAINT [FK_CTSP_XuatXu] FOREIGN KEY(ID_XuatXu) REFERENCES XuatXu(ID),
	CONSTRAINT [FK_CTSP_DanhMuc] FOREIGN KEY(ID_DanhMuc) REFERENCES DanhMuc(ID),
	CONSTRAINT [PK_SanPhamChiTiet] PRIMARY KEY (ID)
)
select * from SanPhamChiTiet

CREATE TABLE Voucher(
	ID INT IDENTITY(1,1) NOT NULL,
	Ma VARCHAR(10) UNIQUE,
	Ten NVARCHAR(30) NOT NULL,
	NgayTao DATETIME DEFAULT GETDATE(),
	GhiChu NVARCHAR(50) NULL,
	TrangThai BIT NOT NULL,
	ID_NhanVien INT NOT NULL,

	CONSTRAINT [PK_Voucher] PRIMARY KEY (ID),
	CONSTRAINT [FK_VoucherCT_NhanVien] FOREIGN KEY(ID_NhanVien) REFERENCES NhanVien(ID)
)


CREATE TABLE VoucherCT(
	ID INT IDENTITY(1,1) NOT NULL,
	NgayBatDau DATE NOT NULL,
	NgayHetHan DATE NOT NULL,
	SoLuong INT NOT NULL,
	KieuGiam Bit NOT NULL,
	ID_Voucher INT NOT NULL,

	CONSTRAINT [FK_VoucherCT_Voucher] FOREIGN KEY(ID_Voucher) REFERENCES Voucher(ID),

	CONSTRAINT [PK_VoucherCT] PRIMARY KEY (ID)
)


CREATE TABLE HoaDon(
	ID INT IDENTITY(1,1) NOT NULL,
	Ma VARCHAR(10) UNIQUE,
	NgayTao DATETIME DEFAULT GETDATE(),
	TongTien MONEY NOT NULL,
	TrangThai BIT NOT NULL,
	GhiChu NVARCHAR(50) NULL,
	
	CONSTRAINT [PK_HoaDon] PRIMARY KEY (ID)
)


CREATE TABLE HoaDonChiTiet(
	ID INT IDENTITY(1,1) NOT NULL,
	GiaBan MONEY NOT NULL,
	SoLuongSP INT NOT NULL,
	TongTien MONEY NOT NULL,
	ID_SanPhamCT INT NOT NULL,
	ID_HoaDon INT NOT NULL,
	ID_VoucherCT INT NULL,
	ID_NhanVien INT NOT NULL,
	ID_KhachHang INT NOT NULL,

	CONSTRAINT [FK_CTHD_SanPhamCT] FOREIGN KEY(ID_SanPhamCT) REFERENCES SanPhamChiTiet(ID),
	CONSTRAINT [FK_CTHD_HoaDon] FOREIGN KEY(ID_HoaDon) REFERENCES HoaDon(ID),
	CONSTRAINT [FK_CTHD_VoucherCT] FOREIGN KEY(ID_VoucherCT) REFERENCES VoucherCT(ID),
	CONSTRAINT [FK_CTHD_NhanVien] FOREIGN KEY(ID_NhanVien) REFERENCES NhanVien(ID),
	CONSTRAINT [FK_CTHD_KhachHang] FOREIGN KEY(ID_KhachHang) REFERENCES KhachHang(ID),

	CONSTRAINT [PK_HoaDonChiTiet] PRIMARY KEY (ID)
)


-- Dữ liệu Size
INSERT INTO [dbo].[Size] ([Ten], [TrangThai]) 
VALUES  (N'35', 1),
		(N'36', 0),
		(N'37', 1),
		(N'38', 0),
		(N'39', 1),
		(N'40', 0),
		(N'41', 1),
		(N'42', 1),
		(N'43', 1);

--Dữ liệu MauSac
INSERT INTO [dbo].[MauSac] ([Ten], [TrangThai]) 
VALUES  (N'Đỏ', 1),
		(N'Trắng', 0),
		(N'Xanh', 1),
		(N'Đen', 0),
		(N'Hồng', 1),
		(N'Vàng', 0),
		(N'Cam', 1);

--Dữ liệu ChatLieu
INSERT INTO [dbo].[ChatLieu] ([Ten], [TrangThai]) 
VALUES  (N'Da', 1),
		(N'vải', 0);

--Dữ liệu XuatXu
INSERT INTO [dbo].[XuatXu] ([Ten], [TrangThai]) 
VALUES  (N'Việt Nam', 1),
		(N'Nhật Bản', 0);

--Dữ liệu ThuongHieu
INSERT INTO [dbo].[ThuongHieu] ([Ten], [TrangThai]) 
VALUES  (N'Nike', 1),
		(N'Puma', 0),
		(N'Asics', 1),
		(N'Balance', 1),
		(N'Adidas', 0);

--Dữ liệu DanhMuc
INSERT INTO [dbo].[DanhMuc] ([Ten], [TrangThai]) 
VALUES  (N'Giày đôi', 1),
		(N'Giày nam', 0),
		(N'Giày nữ', 1),
		(N'Giày thể thao', 1),
		(N'Giày thời trang', 0);

--Dữ liệu KhachHang
INSERT INTO [dbo].[KhachHang]([Ma], [Ten], [NgaySinh], [GioiTinh], [SDT])
VALUES		('KH1', N'Nguyễn Văn A', '2004-11-20', 1, '0987234141'),
			('KH2', N'Nguyễn Văn A', '2004-11-20', 1, '0987234141'),
			('KH3', N'Nguyễn Văn A', '2004-11-20', 0, '0987234141'),
			('KH4', N'Nguyễn Văn A', '2004-11-20', 1, '0987234141'),
			('KH5', N'Nguyễn Văn B', '2004-11-20', 1, '0987234141'),
			('KH6', N'Nguyễn Văn A', '2004-11-20', 0, '0987234141'),
			('KH7', N'Nguyễn Văn A', '2004-11-20', 1, '0987234141'),
			('KH8', N'Nguyễn Văn A', '2004-11-20', 1, '0987234141'),
			('KH9', N'Nguyễn Văn A', '2004-11-20', 1, '0987234141'),
			('KH10', N'Nguyễn Văn A', '2004-11-20', 1, '0987234141');
			

--Dự liệu NhanVien
INSERT INTO [dbo].[NhanVien] ([Ma], [Passwords], [Ten], [SDT], [Email], [ChucVu], [TrangThai])
VALUES		('NV1', '124253463', N'Nguyễn Văn B', '0932432422', 'huyldph40152@fpt.edu.vn', 1, 1),
			('NV2', '124253463', N'Nguyễn Văn B', '0932432422', 'huyldph40152@fpt.edu.vn', 0, 1),
			('NV3', '124253463', N'Nguyễn Văn B', '0932432422', 'huyldph40152@fpt.edu.vn', 1, 1),
			('NV6', '124253463', N'Nguyễn Văn Teo', '0932432422', 'huyldph40152@fpt.edu.vn', 1, 0),
			('NV4', '124253463', N'Nguyễn Văn B', '0932432422', 'huyldph40152@fpt.edu.vn', 0, 1),
			('NV5', '124253463', N'Nguyễn Văn B', '0932432422', 'huyldph40152@fpt.edu.vn', 1, 0),
			('NV7', '124253463', N'Nguyễn Văn B', '0932432422', 'huyldph40152@fpt.edu.vn', 0, 1),
			('NV8', '124253463', N'Nguyễn Văn B', '0932432422', 'huyldph40152@fpt.edu.vn', 0, 0),
			('NV10', '124253463', N'Nguyễn Văn B', '0932432422', 'huyldph40152@fpt.edu.vn', 1, 1),
			('NV9', '124253463', N'Nguyễn Văn B', '0932432422', 'huyldph40152@fpt.edu.vn', 1, 1);

--Dữ liệu SanPham
INSERT INTO [dbo].[SanPham] ([Ma], [TenSP], [MoTa], [ID_ThuongHieu])
VALUES			('SP1', N'Giày Nike nam', null, 1),
				('SP2', N'Giày Nike nữ', null, 1),
				('SP3', N'Giày Puma nam', null, 2),
				('SP4', N'Giày Nike nam', null, 1),
				('SP5', N'Giày Balance nam', null, 4),
				('SP6', N'Giày Nike nam', null, 1),
				('SP7', N'Giày Balance nam', null, 4),
				('SP8', N'Giày Nike nam', null, 1),
				('SP9', N'Giày Nike nam', null, 1),
				('SP10', N'Giày Nike nam', null, 1);
			
--dữ liệu sản phẩm chi tiết
INSERT INTO [dbo].[SanPhamChiTiet] ([TrangThai], [Gia], [SoLuong], [ID_SP], [ID_Size], [ID_MauSac], [ID_ChatLieu], [ID_XuatXu], [ID_DanhMuc])
VALUES			(1, 200, 100, 1, 1, 1, 1, 1, 1),
				(0, 300, 100, 2, 1, 3, 1, 1, 4),
				(0, 200, 200, 1, 1, 1, 1, 2, 1),
				(1, 200, 100, 1, 1, 1, 1, 1, 4),
				(0, 250, 300, 1, 1, 1, 3, 2, 1),
				(1, 200, 100, 1, 1, 4, 1, 1, 1),
				(1, 500, 100, 1, 4, 1, 1, 1, 3),
				(1, 200, 100, 1, 1, 1, 1, 2, 1);

--Dữ liệu Voucher
INSERT INTO [dbo].[Voucher] ([Ma], [Ten], [NgayTao] ,[GhiChu], [TrangThai], [ID_NhanVien])
VALUES			('VC1', N'Giảm giá 20/10', null, null, 1, 1),
				('VC2', N'Giảm giá 20/11', null, null, 1, 3),
				('VC3', N'Giảm giá Halloween', null, null, 0, 1),
				('VC4', N'Giảm giá Valentine', null, null, 1, 2),
				('VC5', N'Giảm giá Tết', null, null, 0, 4),
				('VC6', N'Giảm giá Trung thu', null, null, 1, 1);

--Dữ liệu VoucherCT
INSERT INTO [dbo].[VoucherCT] ([NgayBatDau], [NgayHetHan], [SoLuong], [KieuGiam], [ID_Voucher])
VALUES			('2023-10-20', '2023-10-25', 100, 1, 1),
				('2023-10-20', '2023-10-25', 100, 0, 2),
				('2023-10-20', '2023-10-25', 100, 1, 1),
				('2023-10-20', '2023-10-25', 100, 0, 3),
				('2023-10-20', '2023-10-25', 100, 1, 4),
				('2023-10-20', '2023-10-25', 100, 1, 5);

--Dữ liệu HoaDon
INSERT INTO [dbo].[HoaDon] ([Ma], [NgayTao], [TongTien], [TrangThai], [GhiChu])
VALUES			('HD1', null, 1000, 1, null),
				('HD2', null, 2000, 1, null),
				('HD3', null, 1000, 1, null),
				('HD4', null, 3000, 1, null),
				('HD5', null, 1000, 1, null);


--Dữ liệu HDCT
INSERT INTO [dbo].[HoaDonChiTiet] ([GiaBan], [SoLuongSP], [TongTien], [ID_SanPhamCT], [ID_HoaDon], [ID_VoucherCT], [ID_NhanVien], [ID_KhachHang])
VALUES			(100, 2, 200, 1, 3, 1, 2, 1),
				(100, 1, 100, 2, 1, 1, 1, 2),
				(100, 3, 300, 1, 2, 1, 2, 1),
				(100, 2, 200, 3, 1, 1, 1, 3),
				(100, 4, 400, 1, 2, 1, 3, 1),
				(100, 2, 200, 2, 1, 1, 5, 4)

