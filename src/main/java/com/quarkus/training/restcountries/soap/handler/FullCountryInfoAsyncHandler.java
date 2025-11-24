package com.quarkus.training.restcountries.soap.handler;

import jakarta.xml.ws.AsyncHandler;
import jakarta.xml.ws.Response;
import org.oorsprong.websamples.FullCountryInfoResponse;
import org.oorsprong.websamples.TCountryInfo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FullCountryInfoAsyncHandler implements AsyncHandler<FullCountryInfoResponse> {
    private final CompletableFuture<FullCountryInfoResponse> future;

    public FullCountryInfoAsyncHandler(CompletableFuture<FullCountryInfoResponse> future) {
        this.future = future;
    }

    @Override
    public void handleResponse(Response<FullCountryInfoResponse> response) {
        try {
            FullCountryInfoResponse out = response.get();
            if (out == null) {
                future.completeExceptionally(new RuntimeException("Null SOAP payload"));
                return;
            }
            future.complete(out);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause() != null ? e.getCause() : e;
            future.completeExceptionally(cause);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            future.completeExceptionally(e);
        } catch (Throwable t) {
            future.completeExceptionally(t);
        }
    }
}

