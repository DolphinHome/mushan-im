<template>
	<view>
		打电话
	</view>
</template>

<script>
	export default {
		data() {
			return {
				to:'',
				user:'',
			}
		},
		watch:{
		"$store.state.websocketData": function(val, oldval) {
			if(val.type === 3){

				uni.navigateTo({
					url: '/pages/videoStart/videoStart?user='+this.user+"&to="+val.source
				});
			  }
			}
		},
		onLoad(e){
			this.to = e.to;
			this.user = this.$store.state.user;
			this.toCall(e.to);
		},
		methods: {
			toCall(to){
				let msg = {to:this.to,source:this.user,msg:null,type:2};
				this.$store.dispatch('websocketSend',JSON.stringify(msg))
			}
		}
	}
</script>

<style>

</style>
