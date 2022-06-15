package com.example.aposs_buyer.utils

import com.example.aposs_buyer.model.SuitableRecommend

class CalculateSuitableRecommend private constructor(){

    // set up singleton
    companion object{
        private var instance: CalculateSuitableRecommend? = null
        fun getInstance(): CalculateSuitableRecommend{
            if(instance == null){
                instance = CalculateSuitableRecommend()
            }
            return instance!!
        }
    }
    fun getRecommend(nature: Nature, destiny: Destiny): SuitableRecommend{
        return recommendMap[destiny.value][nature.value]
    }

    fun getExtraRecommend(nature: Nature, destiny: Destiny): String{
        var result ="\t\t"
        for (extraRecommend in recommendMap[destiny.value].stream().map { it.extraRecommend }){
            if(extraRecommend.isNotBlank() && extraRecommend != recommendMap[destiny.value][nature.value].extraRecommend){
                result = "$result$extraRecommend\n \t\t"
            }
        }
        return result
    }

    private var recommendMap = ArrayList<ArrayList<SuitableRecommend>>()
    init {
        val recommendKimKim = SuitableRecommend(
            suitableLever = SuitableLever.TuongUng,
            explain = Destiny.Khon.getMessage(),
            extraRecommend = "/nCác màu tương ứng với Kim bao gồm màu trắng như bạc, ánh kim, bạch kim và thạch anh trắng."
        )
        val recommendKimMoc = SuitableRecommend(
            suitableLever = SuitableLever.CheNgu,
            explain = Destiny.Khon.getMessage(),
            extraRecommend = "Kim sẽ chế khắc được Mộc, nên chọn các gam màu xanh lá cây, xanh da trời. Sự khắc chế này thường dùng để hóa giải những người mệnh Kim có năng lượng quá mạnh, muốn giảm bớt năng lượng để hài hòa với các mệnh khác trong gia đình, tránh xung đột."
        )
        val recommendKimThuy = SuitableRecommend(
            suitableLever = SuitableLever.KhongTuongTac,
            explain = Destiny.Khon.getMessage(),
            extraRecommend = ""
        )
        val recommendKimHoa = SuitableRecommend(
            suitableLever = SuitableLever.TuongKhac,
            explain = Destiny.Khon.getMessage(),
            extraRecommend = "Hỏa sẽ khắc kim vì vậy Nên hạn chế những trang sức gắn đá có màu sắc thuộc hành Hoả như màu đỏ, hồng hay tím, bởi có thể ảnh hưởng đến những may mắn có được trong cuộc sống."
        )
        val recommendKimTho = SuitableRecommend(
            suitableLever = SuitableLever.TuongSinh,
            explain = Destiny.Khon.getMessage(),
            extraRecommend = "Màu sắc tương sinh thường là màu vàng sậm, nâu đất, hổ phách – đây là những màu thuộc hành Thổ mà theo quan hệ tương sinh thì Kim là do Thổ sinh ra."
        )
        val recommendMocKim = SuitableRecommend(
            suitableLever = SuitableLever.TuongKhac,
            explain = Destiny.Chan.getMessage(),
            extraRecommend = "Kim khắc mộc, Người mệnh mộc nên tránh và hạn chế các trang sức có màu trắng, để không bị ảnh hưởng tới năng lượng và may mắn của mình."
        )
        val recommendMocMoc = SuitableRecommend(
            suitableLever = SuitableLever.TuongUng,
            explain = Destiny.Chan.getMessage(),
            extraRecommend = "Để được tương hợp, hòa hợp với người mệnh Mộc chính là màu Mộc, gồm: tất cả màu xanh, như xanh lá cây, xanh da trời…"
        )
        val recommendMocThuy = SuitableRecommend(
            suitableLever = SuitableLever.TuongSinh,
            explain = Destiny.Chan.getMessage(),
            extraRecommend = "Thủy sinh mộc, màu trang sức tốt nhất cho người mệnh mộc là màu ứng với Thủy, gồm các màu xanh nước biển, xanh lam, màu đen, màu xám."
        )
        val recommendMocHoa = SuitableRecommend(
            suitableLever = SuitableLever.KhongTuongTac,
            explain = Destiny.Chan.getMessage(),
            extraRecommend = ""
        )
        val recommendMocTho = SuitableRecommend(
            suitableLever = SuitableLever.CheNgu,
            explain = Destiny.Chan.getMessage(),
            extraRecommend = "Người mệnh Mộc chế được Thổ gồm các màu vàng sậm, nâu đất, gỗ hóa thạch để nên duy trì một cách hài hòa và cân xứng các yếu tố, các mối quan hệ trong cuộc sống."
        )
        val recommendThuyKim = SuitableRecommend(
            suitableLever = SuitableLever.TuongSinh,
            explain = Destiny.Kham.getMessage(),
            extraRecommend = "Theo ngũ hành Kim sinh Thủy, người mệnh Thủy hợp nhất với trang sức ứng với màu Kim như với những loại trang sức như bạc, thạch anh trắng hoặc những sắc ánh kim."
        )
        val recommendThuyMoc = SuitableRecommend(
            suitableLever = SuitableLever.KhongTuongTac,
            explain = Destiny.Kham.getMessage(),
            extraRecommend = ""
        )
        val recommendThuyHoa = SuitableRecommend(
            suitableLever = SuitableLever.CheNgu,
            explain = Destiny.Kham.getMessage(),
            extraRecommend = "Người mệnh Thủy khắc được Hỏa, tức là họ có thể dùng được các màu mà họ chế ngự được như Đỏ, Hồng, Tím – đại diện cho mệnh Hỏa."
        )
        val recommendThuyThuy = SuitableRecommend(
            suitableLever = SuitableLever.TuongUng,
            explain = Destiny.Kham.getMessage(),
            extraRecommend = "Để được tương hợp, mệnh thủy nên chọn đá có màu mệnh Thủy, đại diện của nước như các màu xanh hoặc ánh trắng pha đen."
        )
        val recommendThuyTho = SuitableRecommend(
            suitableLever = SuitableLever.TuongKhac,
            explain = Destiny.Kham.getMessage(),
            extraRecommend = "Thổ khắc Thủy, Tuyệt đối không nên dùng các loại vật phẩm màu vàng sậm, nâu đất vì đó là các màu sắc thuộc hành Thổ sẽ ngăn chặn chế ngự được Thủy, gây bất lợi cho người sở hữu."
        )
        val recommendHoaKim = SuitableRecommend(
            suitableLever = SuitableLever.CheNgu,
            explain = Destiny.Ly.getMessage(),
            extraRecommend = "Vì mệnh Hỏa có thể chế khắc được mệnh Kim, nên cũng có thể đeo trang sức phong thủy có màu thuộc mệnh Kim như: sáng trắng, bạc, bạch kim."
        )
        val recommendHoaMoc = SuitableRecommend(
            suitableLever = SuitableLever.TuongSinh,
            explain = Destiny.Ly.getMessage(),
            extraRecommend = "Trang sức phong thủy tốt nhất cho người mệnh Hỏa nên có màu xanh lá cây, xanh da trời. Các màu này thuộc hành Mộc mà Mộc lại sinh ra Hỏa, rất tốt, giúp tương sinh cho người đeo. "
        )
        val recommendHoaThuy = SuitableRecommend(
            suitableLever = SuitableLever.TuongKhac,
            explain = Destiny.Ly.getMessage(),
            extraRecommend = "Tuyệt đối không được dùng trang sức phong thủy có màu đen, màu xám, màu xanh nước biển (thuộc hành Thủy). Bởi Thủy sẽ dập tắt Hỏa, tương khắc sẽ mang lại vận xui, không tốt cho người thuộc cung mệnh này."
        )
        val recommendHoaHoa = SuitableRecommend(
            suitableLever = SuitableLever.TuongUng,
            explain = Destiny.Ly.getMessage(),
            extraRecommend = "Người mệnh Hỏa cũng được phù trợ về mọi mặt nếu dùng trang sức phong thủy có màu tương hợp là đỏ, hồng, cam, tím (thuộc hành Hỏa)."
        )
        val recommendHoaTho = SuitableRecommend(
            suitableLever = SuitableLever.KhongTuongTac,
            explain = Destiny.Ly.getMessage(),
            extraRecommend = ""
        )
        val recommendThoKim = SuitableRecommend(
            suitableLever = SuitableLever.KhongTuongTac,
            explain = Destiny.Khon.getMessage(),
            extraRecommend = ""
        )
        val recommendThoMoc = SuitableRecommend(
            suitableLever = SuitableLever.TuongKhac,
            explain = Destiny.Khon.getMessage(),
            extraRecommend = "Không nên dùng trang sức màu xanh lá cây, xanh da trời vì chúng đại diện cho hành Mộc, là tương khắc hút hết sự màu mỡ và làm suy kiệt đất, khiến người sử dụng nó vì vậy mà suy yếu về sức khỏe, khó khăn về tài chính."
        )
        val recommendThoThuy = SuitableRecommend(
            suitableLever = SuitableLever.CheNgu,
            explain = Destiny.Khon.getMessage(),
            extraRecommend = "Vì Thổ chế ngự được Thuỷ nên việc đeo những viên đá quý màu xanh, đen sẽ mang lại những điều may mắn cho người mệnh Thổ."
        )
        val recommendThoHoa = SuitableRecommend(
            suitableLever = SuitableLever.TuongSinh,
            explain = Destiny.Khon.getMessage(),
            extraRecommend = "Hỏa sinh Thổ, nên có thể chọn trang sức với những gam màu nóng như màu đỏ."
        )
        val recommendThoTho = SuitableRecommend(
            suitableLever = SuitableLever.TuongUng,
            explain = Destiny.Khon.getMessage(),
            extraRecommend = "Để mang lại sức mạnh tinh thần và sự thoải mái trong tư tưởng, người mệnh Thổ nên sử dụng các loại trang sức màu vàng nhạt, đại diện của sự tương hợp, tạo cảm giác ổn định và nuôi dưỡng. "
        )

        recommendMap.add(
            arrayListOf(recommendKimKim, recommendKimMoc, recommendKimThuy, recommendKimHoa,recommendKimTho  )
        )
        recommendMap.add(
            arrayListOf(recommendMocKim, recommendMocMoc, recommendMocThuy, recommendMocHoa,recommendMocTho  )
        )
        recommendMap.add(
            arrayListOf(recommendThuyKim, recommendThuyMoc, recommendThuyThuy, recommendThuyHoa,recommendThuyTho  )
        )
        recommendMap.add(
            arrayListOf(recommendHoaKim, recommendHoaMoc, recommendHoaThuy, recommendHoaHoa,recommendHoaTho  )
        )
        recommendMap.add(
            arrayListOf(recommendThoKim, recommendThoMoc, recommendThoThuy, recommendThoHoa,recommendThoTho  )
        )
    }
}