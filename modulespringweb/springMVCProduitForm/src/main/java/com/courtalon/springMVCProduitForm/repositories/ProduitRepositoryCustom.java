package com.courtalon.springMVCProduitForm.repositories;

import com.courtalon.springMVCProduitForm.metier.Produit;

public interface ProduitRepositoryCustom {
	Produit findOneWithImage(int id);
}
