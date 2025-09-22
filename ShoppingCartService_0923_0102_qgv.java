// 代码生成时间: 2025-09-23 01:02:39
package com.example.shoppingcart;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/cart")
public class ShoppingCartService {

    // This map simulates a database of cart items, where key is user ID and value is a list of items in the cart
    private Map<String, List<CartItem>> cartDatabase = new HashMap<>();

    public ShoppingCartService() {
        // Initialize the cart database with some dummy data
        cartDatabase.put("user1", new ArrayList<>());
        cartDatabase.put("user2", new ArrayList<>());
    }

    // Class representing a Cart Item
    public static class CartItem {
        private String id;
        private String name;
        private int quantity;

        public CartItem(String id, String name, int quantity) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
        }

        // Getters and setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    @GET
    @Path("/items/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCartItems(@PathParam("userId") String userId) {
        List<CartItem> items = cartDatabase.getOrDefault(userId, new ArrayList<>());
        if (items.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No items found for user: " + userId).build();
        }
        return Response.ok(items).build();
    }

    @POST
    @Path("/add/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItemToCart(@PathParam("userId") String userId, CartItem item) {
        List<CartItem> items = cartDatabase.getOrDefault(userId, new ArrayList<>());
        items.add(item);
        cartDatabase.put(userId, items);
        return Response.ok(item).build();
    }

    // Additional methods like updateItemQuantity, removeItem, etc., can be added here for a complete cart service
}
