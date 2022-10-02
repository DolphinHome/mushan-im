<template>
	<view>
		接电话
		<button @click="meetTo">接听</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				source:'',
			}
		},
		onLoad(e){
			this.source = e.source
		},
		methods: {
			meetTo(){
				//推送消息接听
				let user = this.$store.state.user;
				let msg = {to:this.source,source:user,msg:null,type:3};
				this.$store.dispatch('websocketSend',JSON.stringify(msg))
				//跳转页面进行通话
				
				uni.navigateTo({
					url: '/pages/videoStart/videoStart?user='+user+"&to="+this.source
				});
				
			}
		}
	}
</script>

<style>

</style>
