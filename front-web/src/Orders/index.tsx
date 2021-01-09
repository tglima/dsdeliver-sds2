import './style.css'
import { useEffect, useState } from 'react'
import { fetchProducts } from '../api';
import OrderLocation from './OrderLocation';
import ProductList from './ProductList'
import StepHeader from './StepsHeader'
import { OrderLocationData, Product } from './types';

function Orders()
{
    const [products, setProducts] = useState<Product[]>([]);
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    const [orderLocation, setOrderLocation] = useState<OrderLocationData>();

    useEffect(() => {
        fetchProducts()
            .then(response => setProducts(response.data))
            .catch(error => console.log(error))   
    }, []);

    return (
        <div className="orders-container">
            <StepHeader />
            <ProductList products={products} />
            <OrderLocation onChangeLocation={location => setOrderLocation(location)} />
        </div>
        
    )
}

export default Orders;