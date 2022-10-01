<template>
	<view>
		<scroll-view scroll-y="true">
			<view class="list">
				
				<view v-for="(item,index) in list"  :class="[item.source == user?'item-right':'item-left','item']">
				<u-avatar  :text="item.source == user?user:to"
                          fontSize="18"
                          randomBgColor  shape="square"></u-avatar>
				<view  :class="[item.source == user?'msg-right':'msg-left']">{{item.msg}}</view>
				</view>	
				
			</view>
		</scroll-view>
		<view class="list-boom">
			<view class="boom-item">  
			<u--input border="bottom" clearable shape="square" v-model="msg"></u--input>
			<button type="primary" @click="tomsg">发送</button>
			<u-icon name="phone"  size="38" @click="phoneto"></u-icon>
			</view>
		   </view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				leftAvatar:"https://cdn.uviewui.com/uview/album/2.jpg",
				rightAvatar:"https://cdn.uviewui.com/uview/album/3.jpg",
				msg:"",
				to:"",
				list:[]
			}
		},
		watch:{
		"$store.state.websocketData": function(val, oldval) {
			if(val.type === 1){
				this.list.push(val)
			  }
			}
		},
		onLoad(e) {
			this.getToUser(e.to)
		},
		methods: {
			phoneto(){
				// uni.navigateTo({
				// 	url: '/pages/videoStart/videoStart?from=熊猫'
				// });
				
				//跳转到电话页面
				uni.navigateTo({
					url:'/pages/call/call?to='+this.to
				})
			},
			getToUser(to){
				this.to = to
			},
			tomsg(){
				let msg = {to:this.to,source:this.user,msg:this.msg,type:1};
				this.list.push(msg);
				this.$store.dispatch('websocketSend',JSON.stringify(msg));
				this.msg = null;
			}
		},
		computed:{	
			user(){
				return this.$store.state.user
			}
		}
	}
</script>

<style>
	page{
		background-color: rgb(243,243,243);
	}
	
.list{
	padding-top: 50rpx;
	padding-left: 20rpx;
	padding-right: 20rpx;
	padding-bottom: 100rpx;
	display: flex;
	flex-direction: column;
}
.item{
	display: flex;
	padding: 20rpx 0;
	
}
.item-left{
	flex-direction: row;
}
.msg-left{
	margin-left: 16rpx;
	background-color: #ffffff;
	font-size: 25rpx;
	border-radius: 20rpx;
	line-height: 50rpx;
	padding: 16rpx 14rpx; 
	max-width: 50%;
}

.item-right{
flex-direction: row-reverse;
}
.msg-right{
	margin-right: 16rpx;
	background-color: #ffffff;
	font-size: 28rpx;
	border-radius: 20rpx;
	line-height: 50rpx;
	padding: 16rpx 14rpx;
	max-width: 50%;
}
.list-boom{
        background: #ffffff;
        border-top: 1px solid rgba(39, 40, 50, 0.1);
        width: 100%;
        position: fixed;
        bottom: 0;
        z-index: 100;
        padding-bottom: var(--status-bar-height);
        padding-bottom: env(safe-area-inset-bottom);
		
}
.boom-item{
	display: flex;
}

</style>
