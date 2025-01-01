import request from '@/utils/request'

// 调用AI聊天接口
export function chatWithAI(data) {
  return request({
    url: '/tool/ai/chat',
    method: 'post',
    data: data
  })
} 