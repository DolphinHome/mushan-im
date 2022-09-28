<template>
	<view class="content">
		<view class="list">
			<view class="list-item">
			  <image  class="item-image"  src="https://cdn.uviewui.com/uview/album/1.jpg"></image>
			  <h3 class="item-name">小明</h3>
			  <view class="item-title">你好啊</view>
			  <view class="item-time">2001/01/22</view>
			</view> 
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		onLoad() {

		},
		methods: {

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
