import { Platform } from "react-native";
import dayjs from 'dayjs';
import 'dayjs/locale/pt-br';
import relativeTime  from 'dayjs/plugin/relativeTime';

if (Platform.OS === 'android') {
    require('intl');
    require('intl/locale-data/jsonp/pt-BR'); 
}

dayjs.locale('pt-br');
dayjs.extend(relativeTime);

export function dateFromNow(date: string)
{
    return dayjs(date).fromNow()
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