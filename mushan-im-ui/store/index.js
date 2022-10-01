import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        socketTask: null,
        websocketData: {}, // 存放从后端接收到的websocket数据
		user:"",
    },
    mutations: {
        setWebsocketData (state, data) {
			 let message = JSON.parse(data.data);
			 state.websocketData = message
        },
		setUser(state,user){
			 state.user = user
		},
	
    }, 
    actions: {
        websocketInit ({ state, dispatch }, url) {
            state.socketTast = uni.connectSocket({
                url, // url是websocket连接ip
                success: () => {
                    console.log('websocket连接成功！')
                },
                fail: e => {
                    console.log('连接失败' + e)
                }
            })
            state.socketTast.onOpen(() => dispatch('websocketOnOpen'))
            state.socketTast.onMessage(res => dispatch('websocketOnMessage', res))
            state.socketTast.onClose(e => dispatch('websocketOnClose'))
            state.socketTast.onError(e => dispatch('websocketOnError'))
        },
        websocketOnOpen ({ commit }) {
            console.log('WebSocket连接正常打开中...！')
        },
        // 收到数据
        websocketOnMessage ({ commit }, res) {
            if (res.data !== '连接成功') {
				if(res){
				let data = JSON.parse(res.data);
				if(data){
					if(data.type === 2){
						//跳转到接电话页面
					uni.navigateTo({
						url: '/pages/meet/meet?source='+data.source
					});
					}else{
						 commit('setWebsocketData',res)
					}
				}
				} 
            }
        },
        websocketOnClose ({ commit, dispatch }) {
            console.log('WebSocket连接关闭')
        },
        websocketOnError ({ commit, dispatch }) {
            console.log('WebSocket连接错误')
        },
        websocketClose ({ state }) {
            if (!state.socketTast) return
            state.socketTast.close({
                success (res) {
                    console.log('关闭成功', res)
                },
                fail (err) {
                    console.log('关闭失败', err)
                }
            })
        },
        // 发送数据
        websocketSend ({ state }, data) {
            uni.sendSocketMessage({
                data,
                success: res => {
                    console.log('发送成功', res)
                },
                fail: e => {
                    console.log('发送失败', e)
                }
            })
        }
    }

})

export default store