<template>
	<view class="cent">
		<view class="cent-top">
				 <u-avatar
				            text="木杉"
				            fontSize="18"
				            randomBgColor
				    ></u-avatar>
		</view>
		<view class="cent-boomcent">
			<u-form :model="form" ref="uForm" :rules="rules">
				<u-form-item label="名称" prop="name" label-width="130">
					<u-input v-model="form.name" placeholder="请输入名称" />
				</u-form-item>
			
			</u-form>
		</view>
		<view class="cent-boom">
			<u-button @click="submit" type="primary">提交</u-button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				form: {
					name: undefined,
				},
				rules: {
					name: [{
						required: true,
						message: '请输入名称',
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change', 'blur'],
					}, ],

				}
			}
		},
		onLoad() {
		
		},
		onReady() {
			this.$refs.uForm.setRules(this.rules)
		},
		methods: {
			submit() {
				this.$refs.uForm.validate().then(res => {
					uni.navigateTo({
						url: '/pages/index/index?id='+this.form.name
					});
				}).catch(errors => {
				  uni.$u.toast('请输入名称')
			 })
			}
		}
	}
</script>

<style scoped>
	.cent {
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
	}

	.cent-top {
		margin-top: 20%;
		width: 100%;
		height: 300rpx;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.cent-boom {
		margin-top: 70rpx;
		width: 80%;
	}
</style>