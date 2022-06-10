package com.example.nutritionapplication.service;

import com.example.nutritionapplication.dto.RezepteTO;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RezepteService {
    @GET("/rest/searchbar/{name}/{f}/{l}/{h}/{vegan}/{vegetarisch}/{mink}/{maxk}/{minp}/{maxp}")
    public Call<List<RezepteTO>> listNormal(@Header("Authorization") String Authorization, @Path("name") String name, @Path("f") boolean fructose, @Path("l") boolean lactose,
                                            @Path("h") boolean histamine, @Path("vegan") boolean isVegan, @Path("vegetarisch") boolean isVegetarisch,
                                            @Path("mink") int minK, @Path("maxk") int maxK, @Path("minp") int minP, @Path("maxp") int maxP);

    @Multipart
    @POST("/rest/searchbar/addR/{name}/{az}/{kz}/{p}/{ma}/{iv}/{ivt}/{h}/{l}/{f}")
    public Call<RezepteTO> saveRezept(@Header("Authorization") String Authorization,@Path("name") String rezeptName,
                                      @Path("az") int arbeitszeit,@Path("kz") int kochzeit,
                                      @Path("p") int portionen, @Path("ma") String menueart,
                                      @Path("iv") boolean isVegan, @Path("ivt") boolean isVegetarisch,
                                      @Path("h") boolean h, @Path("l") boolean l, @Path("f") boolean f,
                                      @Part("file") RequestBody file);

    @POST("/rest/searchbar/addF/{id}/{name}/{k}/{p}/{menge}")
    public Call<RezepteTO> addFood(@Path("id")int id,@Path("name") String name, @Path("k") int kalorien, @Path("p") int protein,
                                   @Path("menge") String menge);

    @DELETE("/rest/searchbar/delete/{id}")
    public Call<Void> deleteRezept(@Path("id") int id);

    @DELETE("/rest/searchbar/deleteF/{rId}/{fId}")
    public Call<RezepteTO> deleteFoodFromRezept(@Path("rId") int rId,@Path("fId") int fId);
}
