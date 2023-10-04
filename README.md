# Mock json generator

根据模板生成json返回的mock数据

样例：

``` json
[
  '{{repeat|2,7}}',
  [ 
    '{{repeat|3,4}}',
    {
      "id": "{{snowId}}",
      "gid":"{{uuid}}",
      "name": "{{name}}",
      "age": "{{integer|10,75}}",
      "gender": "{{gender}}",
      "address": "{{address}}",
      "remark": "{{text|10,100}}",
      "loginTime": "{{date|2022-10-01 11:11:11,2023-10-07 11:11:11,yyyy-MM-dd HH:mm:SS}}",
      "money": "{{double|2222.22,231111.22}}",
      "tags": [
      	'{{repeat|6,10}}',
        "{{text|2,10}}"
      ]
    }
  ]
]
```

基本格式为

`function|arg1,arg2...`

如整数（`integer|min,max`）: 

`integer|1,2`   

