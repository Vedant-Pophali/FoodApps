import React, { useEffect, useState } from 'react';
import { assets } from '../../assets/assets';
import axios from "axios";

const Orders = () => {
  const [data, setData] = useState([]);

  const fetchOrders = async () => { 
    try {
      const response = await axios.get("http://localhost:8080/api/order/all");
      console.log("Fetched orders:", response.data);
      setData(response.data);
    } catch (error) {
      console.error("Failed to fetch orders:", error);
    }
  };

  const updateStatus = async (event, orderId) => {
    if (!orderId) {
      console.warn("Order ID missing for updateStatus call");
      return;
    }
    try {
      const response = await axios.patch(
        `http://localhost:8080/api/order/status/${orderId}?status=${event.target.value}`
      );
      if (response.status === 200) {
        await fetchOrders();
      }
    } catch (error) {
      console.error("Failed to update status:", error);
    }
  };

  useEffect(() => {
    fetchOrders();
  }, []);

  return (
    <div className="container">
      <div className="py-5 row justify-content-center">
        <div className="col-11 card">
          <table className="table table-responsive">
            <thead>
              <tr>
                <th></th>
                <th>Items & Address</th>
                <th>Total Price</th>
                <th>Number of Items</th>
                <th>Refresh</th>
              </tr>
            </thead>
            <tbody>
              {data.length > 0
                ? data.map((order, index) => (
                    <tr key={index}>
                      <td>
                        <img
                          src={assets.parcel}
                          alt="Delivery Icon"
                          height={48}
                          width={48}
                        />
                      </td>

                      <td>
                        {Array.isArray(order.orderedItems) &&
                        order.orderedItems.length > 0 ? (
                          <ul style={{ paddingLeft: "20px", margin: 0 }}>
                            {order.orderedItems.map((item, idx) => (
                              <li key={idx}>
                                {idx + 1}. {item.name} x {item.quantity}
                              </li>
                            ))}
                          </ul>
                        ) : (
                          <span>No items</span>
                        )}

                        {order.userAddress && (
                          <div
                            style={{
                              marginTop: "8px",
                              fontStyle: "italic",
                              color: "#555",
                            }}
                          >
                            Address: {order.userAddress}
                          </div>
                        )}
                      </td>

                      <td>&#x20B9;{order.amount.toFixed(2)}</td>

                      <td>
                        Items:{" "}
                        {Array.isArray(order.orderedItems)
                          ? order.orderedItems.length
                          : 0}
                      </td>

                      <td>
                        <select
                          className="form-control"
                          onChange={(event) => {
                            if (order.id) {
                              updateStatus(event, order.id);
                            } else {
                              console.warn("Order ID missing for order:", order);
                            }
                          }}
                          value={order.orderStatus}
                        >
                          <option value="Food Preparing">Food Preparing</option>
                          <option value="Out for Delivery">Out for Delivery</option>
                          <option value="Delivered">Delivered</option>
                        </select>
                      </td>
                    </tr>
                  ))
                : null}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Orders;