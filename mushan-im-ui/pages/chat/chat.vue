<template>
	<view>
		<scroll-view scroll-y="true" class="scor">
			<view class="list">
				<view >
					<view class="item item-left">
						<u-avatar
						           text="木杉"
						           fontSize="18"
						           randomBgColor></u-avatar>
							 <view  class="msg-left">hhhhhxiao </view>
					</view>	
				</view>
				<view>
				<view class="item item-right">
				<u-avatar
				           text="木杉"
				           fontSize="18"
				           randomBgColor></u-avatar>
				   			 <view  class="msg-right">hhhhhxiao </view>
				
			  </view>
				</view>
			
			</view>
		</scroll-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
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
	line-height: 44rpx;
	padding: 16rpx 14rpx;
}

.item-right{
flex-direction: row-reverse;
}
.msg-right{
	margin-right: 16rpx;
	background-color: #55ffff;
	font-size: 25rpx;
	border-radius: 20rpx;
	line-height: 44rpx;
	padding: 16rpx 14rpx;
}

</style>
