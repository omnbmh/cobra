import axios from 'axios'
import {message} from 'element-ui'

axios.interceptors.request.use(config => { return config;},err => {Message.error({message:'request error'});})
axios.interceptors.response.use(data => {
    if(data.status && data.status == 200 && data.data.status == 500){
        Message.error({message:data.data.msg});
        return;
    }
    if(data.data.msg){
        Message.success({message:data.data.msg});
    }
    return data;
},err => {
    if(err.response.status == 504 || err.response.status == 404){
        Message.error({message:'server lost'});
    }
    else if(err.response.status == 403){
        Message.error({message:'没权限'});
    }
    else if(err.response.status == 401){
        Message.error({message:err.response.data.msg});
    }else{
        if(err.response.data.msg){
            Message.error({message:err.response.data.msg});
        }else{
            Message.error({message:'咋了呢昵'});
        }
    }
})

let base = '';

export const postRequest = (url,params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data:params,
        transformRequest:[function (data){
            let ret = ''
            for (let it in data){
                ret += encodeURIComponent(it)+'='+encodeURIComponent(data[it])+'&'
            }
            return ret
        }],
        headers:{
            'Content-Type':'application/x-www-form-urlencoded'
        }
    });
}

export const getRequest= (url) => {
    return axios({
        method: 'get',
        url : `${base}${url}`
    });
}
