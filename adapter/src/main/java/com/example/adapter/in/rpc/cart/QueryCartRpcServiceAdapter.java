package com.example.adapter.in.rpc.cart;

import com.example.iface.cart.Cart;
import com.example.iface.cart.QueryCartRpcService;
import org.springframework.stereotype.Service;

@Service
public class QueryCartRpcServiceAdapter implements QueryCartRpcService {
  @Override
  public Cart queryCart(long customerId) {
    return null;
  }
}
