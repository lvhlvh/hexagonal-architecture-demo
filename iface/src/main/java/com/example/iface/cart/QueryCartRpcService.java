package com.example.iface.cart;

// rpc IDL 定义
public interface QueryCartRpcService {
  Cart queryCart(long customerId);
}
