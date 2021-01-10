import { useIsFocused, useNavigation } from '@react-navigation/native';
import React, { useEffect, useState } from 'react';
import { StyleSheet, Text, Alert, View } from 'react-native';
import { ScrollView, TouchableWithoutFeedback } from 'react-native-gesture-handler';
import { fetchOrders } from '../api';
import Header from '../Header';
import OrderCard from '../OrderCard';
import { Order } from '../types';

function Orders() {
  
  const [orders, setOrders] = useState<Order[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const navigation = useNavigation();
  const isFocused = useIsFocused();
  const fetchData = () => {
    setIsLoading(true);
    fetchOrders()
    .then(response => setOrders(response.data))
    .catch(error =>  Alert.alert("Ocorreu um erro ao buscar os pedidos"))
    .finally(() => setIsLoading(false));
  }

  useEffect(() => {
    if(isFocused)
    {
      fetchData();
    }    
  }, [isFocused]);


  const handleOnPress = (order: Order) => {
    navigation.navigate('OrderDetails', {order});
  };

  return (
    <>
      <Header />
      <ScrollView style={styles.container}>
        {isLoading ? (
          <View style={styles.containerLoad}>
            <Text style={styles.textLoad}>Carregando pedidos...</Text>
          </View>
        ) :
        (
          orders.map(order => (
            <TouchableWithoutFeedback key={order.id} onPress={() => {
              handleOnPress(order)
            }}>
              <OrderCard order={order} />
            </TouchableWithoutFeedback>
          ))
        )}
      </ScrollView>
    </>
  );
}

const styles = StyleSheet.create(
  {
    container:{
      paddingRight: '5%',
      paddingLeft: '5%'
    },
    containerLoad:{
      flex: 1,
      justifyContent: 'center',
      alignItems: 'center'
    },
    textLoad:{
      paddingTop: '50%',
      fontSize: 24,
      color: '#DA5C5C'
    }
  }
);

export default Orders;