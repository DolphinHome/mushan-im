<template>
	<view class="content">
		<view class="list">
			<view class="list-item"  v-for="(item,index) in list" @click="toChat(item)">
			  <image  class="item-image"  src="https://cdn.uviewui.com/uview/album/1.jpg"></image>
			  <h3 class="item-name">{{item}}</h3>
			  <view class="item-title">我是{{item}}</view>
			  <view class="item-time">2001/01/22</view>
			</view> 
		</view>
		<u-toast ref="uToast"></u-toast>
	</view>
	
</template>

<script>
	export default {
		data() {
			return {
				list:[],
			}
		},
		watch:{
		"$store.state.websocketData": function(val, oldval) {
			if(val.type === 0){
				this.$refs.uToast.show({
					type: 'success',
					title: '好友上线',
					message: val.msg,
					iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/jump.png'
				})
				this.getlist()
			  }
			}
		},
		onLoad(e) {
			this.initWebsocket(e);
			this.getlist();
		},
		methods: {
			getlist(){
				uni.request({
				    url: 'http://192.168.0.101:8080/user/list', //仅为示例，并非真实接口地址。
				    success: (res) => {
				      let list =  res.data
					  this.list = this.remove(list,this.$store.state.user)
				    }
				});	
			},
			remove(list,user){
				var index = list.indexOf(user); 
				if (index > -1) { 
				list.splice(index, 1); 
				} 
				return list;
			},
			initWebsocket(e){
				this.$store.commit('setUser',e.id);
				this.$store.dispatch('websocketInit', 'ws://192.168.0.101:8080/mushan/'+e.id)
			},
			 toChat(e){	
				uni.navigateTo({
					url: '/pages/chat/chat?to='+e
				});
			}
		}
	}
</script>

<style>
	.list-item{
		margin-top: 30rpx;
		display: grid;
		grid-template-columns: repeat(3,1fr);
		grid-template-rows:repeat(2,1fr);
		justify-items: center;
		align-items:center;

		grid-template-areas: 'a b d'
		                     'a c d';
							 
	}
	.item-image{
		border-radius: 20rpx;
		width: 100rpx;
		height: 100rpx;
		grid-area: a;
	}
	.item-name{
		justify-self: start;
		grid-area: b;
	}
	.item-time{
		color: rgb(194,194,194);
		align-self: start;
		grid-area: d;
	}
	.item-title{
		justify-self: start;
		color: #959595;
		grid-area: c;
	}
</style>
