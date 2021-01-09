import { OrderLocationData, Product } from "./types";
import { toast } from 'react-toastify';

export const initialLatitude = -27.6977292;
export const initialLongitude = -48.4691449;

export function checkIsSelected(selectedProducts: Product[], product: Product)
{
    return selectedProducts.some(item => item.id === product.id);
}


export function formatPrice(price: number)
{
    const formatter = new Intl.NumberFormat('pt-BR', {
        style: 'currency',
        currency: 'BRL',
        minimumFractionDigits: 2
    });

    return formatter.format(price);
}

export function validateOrder(selectedProducts: Product[], orderLocation: OrderLocationData)
{

    if(selectedProducts.length === 0)
    {
      toast.warning('Selecione pelo menos um pedido!');
      return false;
    }

    if(orderLocation === undefined 
        || (orderLocation.latitude === undefined && orderLocation.longitude))
    {
        toast.warning('Selecione o endereço de entrega!');
        return false;
    }

    if(orderLocation.latitude === initialLatitude 
        && orderLocation.longitude === initialLongitude)
    {
        toast.warning('Selecione outro endereço de entrega!');
        return false;
    }

    return true;
}