-- Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd.
CREATE TABLE pos_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(32) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL,
    full_name VARCHAR(50) NOT NULL, role VARCHAR(20) NOT NULL, store_name VARCHAR(80), enabled BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME(6) NOT NULL, updated_at DATETIME(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE pos_product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT, sku VARCHAR(40) NOT NULL UNIQUE, barcode VARCHAR(40) NOT NULL UNIQUE,
    name VARCHAR(120) NOT NULL, category VARCHAR(40) NOT NULL, price DECIMAL(12,2) NOT NULL,
    member_price DECIMAL(12,2) NOT NULL, stock INT NOT NULL, status VARCHAR(20) NOT NULL, color_code VARCHAR(20) NOT NULL,
    created_at DATETIME(6) NOT NULL, updated_at DATETIME(6) NOT NULL,
    INDEX idx_pos_product_category (category), INDEX idx_pos_product_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE pos_store (
    id BIGINT PRIMARY KEY AUTO_INCREMENT, store_code VARCHAR(24) NOT NULL UNIQUE, name VARCHAR(80) NOT NULL,
    city VARCHAR(40) NOT NULL, address VARCHAR(120) NOT NULL, status VARCHAR(16) NOT NULL, register_count INT NOT NULL,
    today_sales DECIMAL(14,2) NOT NULL, today_orders INT NOT NULL, created_at DATETIME(6) NOT NULL, updated_at DATETIME(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE pos_member (
    id BIGINT PRIMARY KEY AUTO_INCREMENT, member_no VARCHAR(24) NOT NULL UNIQUE, name VARCHAR(50) NOT NULL,
    mobile VARCHAR(20) NOT NULL UNIQUE, level VARCHAR(20) NOT NULL, points INT NOT NULL,
    balance DECIMAL(12,2) NOT NULL, total_spend DECIMAL(14,2) NOT NULL, created_at DATETIME(6) NOT NULL, updated_at DATETIME(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE pos_cashier_shift (
    id BIGINT PRIMARY KEY AUTO_INCREMENT, shift_no VARCHAR(32) NOT NULL UNIQUE, store_name VARCHAR(80) NOT NULL,
    register_no VARCHAR(24) NOT NULL, cashier_name VARCHAR(40) NOT NULL, status VARCHAR(12) NOT NULL,
    opened_at DATETIME(6) NOT NULL, closed_at DATETIME(6), opening_cash DECIMAL(12,2) NOT NULL,
    expected_cash DECIMAL(12,2) NOT NULL, order_count INT NOT NULL, created_at DATETIME(6) NOT NULL, updated_at DATETIME(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE pos_order (
    id BIGINT PRIMARY KEY AUTO_INCREMENT, order_no VARCHAR(32) NOT NULL UNIQUE, store_name VARCHAR(80) NOT NULL,
    register_no VARCHAR(24) NOT NULL, cashier_name VARCHAR(40) NOT NULL, member_name VARCHAR(50), payment_method VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL, subtotal DECIMAL(14,2) NOT NULL, discount DECIMAL(14,2) NOT NULL,
    payable DECIMAL(14,2) NOT NULL, paid DECIMAL(14,2) NOT NULL, item_count INT NOT NULL,
    created_at DATETIME(6) NOT NULL, updated_at DATETIME(6) NOT NULL,
    INDEX idx_pos_order_created (created_at), INDEX idx_pos_order_store (store_name), INDEX idx_pos_order_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE pos_order_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT, order_no VARCHAR(32) NOT NULL, sku VARCHAR(40) NOT NULL,
    product_name VARCHAR(120) NOT NULL, unit_price DECIMAL(12,2) NOT NULL, quantity INT NOT NULL,
    line_amount DECIMAL(14,2) NOT NULL, created_at DATETIME(6) NOT NULL, updated_at DATETIME(6) NOT NULL,
    INDEX idx_pos_order_item_order (order_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
