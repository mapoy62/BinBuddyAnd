interface ExploreApi{
    @GET("/igprofile")
    fun getInstagramProfiles(): Call<List<InstagramProfile>>
}