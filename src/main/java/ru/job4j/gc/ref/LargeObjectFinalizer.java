package ru.job4j.gc.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

public class LargeObjectFinalizer extends PhantomReference<Object> {

    public LargeObjectFinalizer(
            Object referent, ReferenceQueue<? super Object> q) {
        super(referent, q);
    }

    public void finalizeResources() {
        System.out.println("clearing ...");
    }

    public static void main(String[] args) {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        List<LargeObjectFinalizer> references = new ArrayList<>();
        List<Object> largeObjects = new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            Object largeObject = new Object();
            largeObjects.add(largeObject);
            references.add(new LargeObjectFinalizer(largeObject, referenceQueue));
        }

        largeObjects = null;
        System.gc();

        Reference<?> referenceFromQueue;
        for (PhantomReference<Object> reference : references) {
            System.out.println(reference.isEnqueued());
        }

        while ((referenceFromQueue = referenceQueue.poll()) != null) {
            ((LargeObjectFinalizer) referenceFromQueue).finalizeResources();
            referenceFromQueue.clear();
        }

    }
}